package com.bsleek.webauthndemo.service;


import com.bsleek.webauthndemo.exception.InvalidKeyException;
import com.bsleek.webauthndemo.dao.CredentialDAO;
import com.bsleek.webauthndemo.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upokecenter.cbor.CBORObject;
import com.upokecenter.cbor.CBORType;
import com.upokecenter.cbor.ICBORConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.security.auth.login.CredentialNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class CredentialService {

    @Autowired
    private CredentialDAO credentialDAO;


    public String retrieveCredentialId(String userName) throws CredentialNotFoundException {
        return CredentialDAO.retrieveCredentialId(userName);
    }

    public void validateAndSave(PublicKeyCredential publicKeyCredential, String userName) throws
            InvalidKeyException, IOException {

        AuthenticatorAttestationResponse authenticatorAttestationResponse =
                publicKeyCredential.getAuthenticatorAttestationResponse();
        Attestation attestation = authenticatorAttestationResponse.getAttestation();
        String authData64 = attestation.getAuthData();
        byte[] authData = Base64.getDecoder().decode(authData64);

        System.out.println("Total length of auth data: " + authData.length);

        byte[] credentialLengthBytes = Arrays.copyOfRange(authData, 53, 55);

        BigInteger bigInteger = new BigInteger(credentialLengthBytes);
        System.out.println("Credential Id Length:" + bigInteger);


        // guess its all zero
        byte[] aaguid = Arrays.copyOfRange(authData, 35, 51);
        System.out.println("guid: ");
        for (byte b : aaguid) {
            System.out.println(b);

        }

        int credentialLength = bigInteger.intValue();
        byte[] credentialIdBytes = Arrays.copyOfRange(authData, 55, 55 + credentialLength);


        String credentialId = Base64.getEncoder().encodeToString(credentialIdBytes);
        System.out.println("Credential Id (Base 64) " + credentialId);




        // ? + 2 + 64 + 77
        byte[] publicKeyBytes = Arrays.copyOfRange(authData, 55 + credentialLength, authData.length);
        System.out.println("public key bytes length: " + publicKeyBytes.length);


        CBORObject cborObject = CBORObject.Read(new ByteArrayInputStream(publicKeyBytes));
        System.out.println("type is " + cborObject.getType());


        Map<String, CBORObject> keys = new HashMap<>();
        cborObject.getKeys().stream().forEach(key ->
        {
            System.out.println("key found:" + key +
                    " value: " + cborObject.get(key) + "type: " + cborObject.get(key).getType() +
                    "key type:" + key.getType());

            keys.put(key.toString(), key);
        });


        System.out.println("finished message parse");

        int numberThree = (int) authData[55];
        System.out.println(numberThree);


        // get simple value returns int for CBOR Object
        int type = Integer.parseInt(cborObject.get(keys.get("1")).toString());
        int algorithm = Integer.parseInt(cborObject.get(keys.get("3")).toString());
        int curveType = Integer.parseInt(cborObject.get(keys.get("-1")).toString());
//
        // ported from c# hence method "names'"
        byte[] x = cborObject.get(keys.get("-2")).GetByteString();
        byte[] y = cborObject.get(keys.get("-3")).GetByteString();


        if (type != 2) {
            throw new InvalidKeyException("Invalid type");
        } else if (algorithm != -7) {
            throw new InvalidKeyException("Invalid algoritm");
        } else if (curveType != 1) {
            throw new InvalidKeyException("Invalid curve type");
        }

        if (x == null || y == null) {
            throw new InvalidKeyException("Invalid coordinates");
        }

        WebAuthnPublicKey webAuthnPublicKey = new WebAuthnPublicKey(credentialId, x, y, algorithm, type, curveType);
        credentialDAO.save(userName, webAuthnPublicKey);

    }


    public boolean validateSignature(LoginRequest loginRequest) throws
            NoSuchAlgorithmException, NoSuchProviderException, InvalidParameterSpecException,
            InvalidKeySpecException, java.security.InvalidKeyException, SignatureException, CredentialNotFoundException, JsonProcessingException {


        WebAuthnPublicKey key =
                CredentialDAO.load(loginRequest.getAuthenticatorAssertionResponse().getUserHandle());

        byte[] signature = Base64.getDecoder()
                .decode(loginRequest.getAuthenticatorAssertionResponse().getSignature());



        // base64 encode clientdatajson
        // call get bytes
        // sha256 it
        // append this to random


        ObjectMapper objectMapper = new ObjectMapper();
        String clientDataJson = objectMapper.writeValueAsString(loginRequest.getAuthenticatorAssertionResponse().getClientData());
        System.out.println("clientDataJson is " + clientDataJson);
        String base64ClientDataJson = Base64.getEncoder().encodeToString(clientDataJson.getBytes());
        byte[] clientDataJsonBytes = Base64.getDecoder().decode(base64ClientDataJson);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedClientDataJsonBytes = digest.digest(clientDataJsonBytes);

        String authenticatorData = loginRequest.getAuthenticatorAssertionResponse().getAuthenticatorData();
        byte[] authenticatorDataBytes = Base64.getDecoder().decode(authenticatorData);

//        byte[] payload = authenticatorDataBytes + hashedClientDataJsonBytes;

        byte[] payload = new byte[authenticatorDataBytes.length + hashedClientDataJsonBytes.length];
        System.arraycopy(authenticatorDataBytes, 0, payload, 0, authenticatorDataBytes.length);
        System.arraycopy(hashedClientDataJsonBytes, 0, payload, authenticatorDataBytes.length, hashedClientDataJsonBytes.length);


        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");

        ECPoint ecPoint = new ECPoint(new BigInteger(key.getX()), new BigInteger(key.getY()));

        // Not varying curve type because we know it will be p256 for yubico

        // from https://www.codota.com/code/java/classes/java.security.spec.ECGenParameterSpec
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("EC", "SunEC");
        parameters.init(new ECGenParameterSpec("secp256r1"));
        ECParameterSpec ecParameters = parameters.getParameterSpec(ECParameterSpec.class);
        ECPublicKeySpec pubKeySpec = new ECPublicKeySpec(ecPoint, ecParameters);
        PublicKey publicKey = KeyFactory.getInstance("EC", "SunEC").generatePublic(pubKeySpec);


        ecdsaSign.initVerify(publicKey);
        ecdsaSign.update(payload);
        return ecdsaSign.verify(signature);
    }
}
