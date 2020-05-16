package com.bsleek.webauthndemo.model;

public class RegistrationRequest {

    private String name;
    private PublicKeyCredential publicKeyCredential;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PublicKeyCredential getPublicKeyCredential() {
        return publicKeyCredential;
    }

    public void setPublicKeyCredential(PublicKeyCredential publicKeyCredential) {
        this.publicKeyCredential = publicKeyCredential;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "name='" + name + '\'' +
                ", publicKeyCredential=" + publicKeyCredential +
                '}';
    }
}
