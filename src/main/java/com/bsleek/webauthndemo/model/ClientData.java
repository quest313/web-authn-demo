package com.bsleek.webauthndemo.model;

public class ClientData {

    private String challenge;
    private String origin;
    private String type;

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "challenge='" + challenge + '\'' +
                ", origin='" + origin + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
