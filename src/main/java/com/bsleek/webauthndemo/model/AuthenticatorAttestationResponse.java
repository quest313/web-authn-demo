package com.bsleek.webauthndemo.model;

public class AuthenticatorAttestationResponse {

    ClientData clientData;
    Attestation attestation;

    public ClientData getClientData() {
        return clientData;
    }

    public void setClientData(ClientData clientData) {
        this.clientData = clientData;
    }

    public Attestation getAttestation() {
        return attestation;
    }

    public void setAttestation(Attestation attestation) {
        this.attestation = attestation;
    }

    @Override
    public String toString() {
        return "AuthenticatorAttestationResponse{" +
                "clientData=" + clientData +
                ", attestation=" + attestation +
                '}';
    }
}
