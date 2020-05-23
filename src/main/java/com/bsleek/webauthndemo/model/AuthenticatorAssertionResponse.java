package com.bsleek.webauthndemo.model;

public class AuthenticatorAssertionResponse {

    String authenticatorData;
    ClientData clientData;
    String signature;
    String userHandle;

    public AuthenticatorAssertionResponse(String authenticatorData, ClientData clientData, String signature, String userHandle) {
        this.authenticatorData = authenticatorData;
        this.clientData = clientData;
        this.signature = signature;
        this.userHandle = userHandle;
    }

    public String getAuthenticatorData() {
        return authenticatorData;
    }

    public void setAuthenticatorData(String authenticatorData) {
        this.authenticatorData = authenticatorData;
    }

    public ClientData getClientData() {
        return clientData;
    }

    public void setClientData(ClientData clientData) {
        this.clientData = clientData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }
}
