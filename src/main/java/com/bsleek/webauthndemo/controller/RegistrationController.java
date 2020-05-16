package com.bsleek.webauthndemo.controller;


import com.bsleek.webauthndemo.model.*;
import com.bsleek.webauthndemo.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/registration/credential")
    public void saveCredential(@RequestBody RegistrationRequest registrationRequest) throws Exception {
        System.out.println(registrationRequest);
        credentialService.validateAndSave(registrationRequest.getPublicKeyCredential(), registrationRequest.getName());
    }

    @PostMapping(value = "/login/challenge")
    public LoginChallengeResponse login(@RequestBody LoginChallengeRequest loginChallengeRequest) throws Exception {
        System.out.println(loginChallengeRequest);
        String credentialId = credentialService.retrieveCredentialId(loginChallengeRequest.getName());
        return new LoginChallengeResponse(credentialId);
    }


}
