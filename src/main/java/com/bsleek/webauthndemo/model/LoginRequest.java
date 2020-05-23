package com.bsleek.webauthndemo.model;

/**
 *  Used for navigator.credentials.get
 */
public class LoginRequest {

    private String id;
    private String type;
    private AuthenticatorAssertionResponse authenticatorAssertionResponse;

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

    public AuthenticatorAssertionResponse getAuthenticatorAssertionResponse() {
        return authenticatorAssertionResponse;
    }

    public void setAuthenticatorAssertionResponse(AuthenticatorAssertionResponse authenticatorAssertionResponse) {
        this.authenticatorAssertionResponse = authenticatorAssertionResponse;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", authenticatorAssertionResponse=" + authenticatorAssertionResponse +
                '}';
    }
}
