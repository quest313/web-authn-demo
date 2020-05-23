package com.bsleek.webauthndemo.controller;


import com.bsleek.webauthndemo.exception.InvalidSignatureException;
import com.bsleek.webauthndemo.model.*;
import com.bsleek.webauthndemo.service.CredentialService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;


@RestController
public class RegistrationController {

    @Autowired
    private CredentialService credentialService;


    @RequestMapping(value = "/registration/challenge", method = RequestMethod.POST)
    public RegistrationResponse register(@RequestBody RegistrationChallengeRequest registrationChallengeRequest) {
        System.out.println(registrationChallengeRequest.getName());
        return new RegistrationResponse();
    }

    @PostMapping(value = "/registration/credential") /// why do we need credential
    public void saveCredential(@RequestBody RegistrationRequest registrationRequest) throws Exception {
        System.out.println(registrationRequest);
        credentialService.validateAndSave(registrationRequest.getPublicKeyCredential(), registrationRequest.getName());
    }

    @PostMapping(value = "/login/challenge")
    public LoginChallengeResponse loginChallenge(@RequestBody LoginChallengeRequest loginChallengeRequest) throws Exception {
        System.out.println(loginChallengeRequest);
        String credentialId = credentialService.retrieveCredentialId(loginChallengeRequest.getName());
        return new LoginChallengeResponse(credentialId);
    }

    @PostMapping(value = "/login/credential")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody LoginRequest loginRequest) throws Exception {
        System.out.println(loginRequest);
        if(!credentialService.validateSignature(loginRequest)){
            throw new InvalidSignatureException("invalid");
        }
        return;
    }
}
