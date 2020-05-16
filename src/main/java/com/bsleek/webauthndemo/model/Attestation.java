package com.bsleek.webauthndemo.model;

public class Attestation {

    private String format;
    private AttestationStatement attestationStatement;
    private String authData;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public AttestationStatement getAttestationStatement() {
        return attestationStatement;
    }

    public void setAttestationStatement(AttestationStatement attestationStatement) {
        this.attestationStatement = attestationStatement;
    }

    public String getAuthData() {
        return authData;
    }

    public void setAuthData(String authData) {
        this.authData = authData;
    }

    @Override
    public String toString() {
        return "Attestation{" +
                "format='" + format + '\'' +
                ", attestationStatement=" + attestationStatement +
                ", authData='" + authData + '\'' +
                '}';
    }
}
