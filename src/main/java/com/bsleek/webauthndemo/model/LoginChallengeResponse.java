package com.bsleek.webauthndemo.model;

public class LoginChallengeResponse {

    String credentialId;

    public LoginChallengeResponse(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }
}
