package com.bsleek.webauthndemo.model;

public class LoginChallengeResponse {

    String credentialId;
    String random = "somethingreallyrandom";

    public LoginChallengeResponse(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
