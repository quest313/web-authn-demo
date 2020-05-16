package com.bsleek.webauthndemo.model;

public class AttestationStatement {

    private String signature;
    private String certificate;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "AttestationStatement{" +
                "signature='" + signature + '\'' +
                ", certificate='" + certificate + '\'' +
                '}';
    }
}
