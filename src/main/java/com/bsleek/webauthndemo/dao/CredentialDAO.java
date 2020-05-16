package com.bsleek.webauthndemo.dao;

import com.bsleek.webauthndemo.model.WebAuthnPublicKey;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.HashMap;
import java.util.Map;

/*

 Typically this would save to a database, but storing in memory for example
 */

@Service
public class CredentialDAO {



    static Map<String, String> userToCredMap = new HashMap<>();
    static Map<String, WebAuthnPublicKey> keyMap = new HashMap<String, WebAuthnPublicKey>();


    public static void save(String username, String credentialId, WebAuthnPublicKey webAuthnPublicKey){
        userToCredMap.put(username, credentialId);
        keyMap.put(credentialId, webAuthnPublicKey);

    }


    public static String retrieveCredentialId(String user){
        return userToCredMap.get(user);
    }

    public static WebAuthnPublicKey load(String username) throws CredentialNotFoundException{
        if(!userToCredMap.containsKey(username)){
            throw new CredentialNotFoundException("Credential not found for:" + username);
        }
        return keyMap.get(userToCredMap.get(username));
    }
}
