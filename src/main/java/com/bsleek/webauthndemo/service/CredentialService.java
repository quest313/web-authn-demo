package com.bsleek.webauthndemo.service;


import com.bsleek.webauthndemo.exception.InvalidKeyException;
import com.bsleek.webauthndemo.dao.CredentialDAO;
import com.bsleek.webauthndemo.model.Attestation;
import com.bsleek.webauthndemo.model.AuthenticatorAttestationResponse;
import com.bsleek.webauthndemo.model.PublicKeyCredential;
import com.bsleek.webauthndemo.model.WebAuthnPublicKey;
import com.upokecenter.cbor.CBORObject;
import com.upokecenter.cbor.CBORType;
import com.upokecenter.cbor.ICBORConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.security.auth.login.CredentialNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
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

        System.out.println("total length" + authData.length);

        byte[] credentialLengthBytes = Arrays.copyOfRange(authData, 53, 55);

        BigInteger bigInteger = new BigInteger(credentialLengthBytes);
        System.out.println(bigInteger);


        // guess its all zero
        byte[] aaguid = Arrays.copyOfRange(authData, 35, 51);
        System.out.println("guid: ");
        for (byte b : aaguid) {
            System.out.println(b);

        }

        int credentialLength = bigInteger.intValue();
        byte[] credentialIdBytes = Arrays.copyOfRange(authData, 55, 55 + credentialLength);

        BigInteger credentialId = new BigInteger(credentialIdBytes);
        System.out.println("credential id: " + credentialId);


        // ? + 2 + 64 + 77
        byte[] publicKeyBytes = Arrays.copyOfRange(authData, 55 + credentialLength, authData.length);
        System.out.println("public key bytes length: " + publicKeyBytes.length);


//        CBORFactory f = new CBORFactory();
//        ObjectMapper objectMapper = new ObjectMapper(f);
//
//        Map<Object, Object> publicKeyMap = objectMapper.readValue(publicKeyBytes, Map.class);
//
//
//
//        System.out.println("Total keys" + publicKeyMap.keySet().size());
//        publicKeyMap.keySet().stream().forEach(key -> System.out.println(key));
//        System.out.println("done print keys");
//        publicKeyMap.keySet().stream().forEach(key -> System.out.println(publicKeyMap.get(key)));
//        System.out.println("done print value");
//
//        byte [] nextBytes = (byte[]) publicKeyMap.get("1");
//
//        Map<Object, Object> nextMap = objectMapper.readValue(nextBytes, Map.class);
//
//        System.out.println("Total next keys" + publicKeyMap.keySet().size());

//        try {
//            Message message = Message.DecodeFromBytes(publicKeyBytes);
//        } catch (CoseException e) {
//            e.printStackTrace();
//        }

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
        credentialDAO.save(userName, publicKeyCredential.getId(), webAuthnPublicKey);

    }
}
