package com.bsleek.webauthndemo.model;

public class PublicKeyCredential {

    private String id;
    private String type;
    private AuthenticatorAttestationResponse authenticatorAttestationResponse;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AuthenticatorAttestationResponse getAuthenticatorAttestationResponse() {
        return authenticatorAttestationResponse;
    }

    public void setAuthenticatorAttestationResponse(AuthenticatorAttestationResponse authenticatorAttestationResponse) {
        this.authenticatorAttestationResponse = authenticatorAttestationResponse;
    }

    @Override
    public String toString() {
        return "PublicKeyCredential{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", authenticatorAttestationResponse=" + authenticatorAttestationResponse +
                '}';
    }
}
