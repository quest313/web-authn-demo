package com.bsleek.webauthndemo.model;

public class ClientData {

    private String type;
    private String challenge;
    private String origin;
    private boolean crossOrigin;


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

    public boolean isCrossOrigin() {
        return crossOrigin;
    }

    public void setCrossOrigin(boolean crossOrigin) {
        this.crossOrigin = crossOrigin;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "challenge='" + challenge + '\'' +
                ", origin='" + origin + '\'' +
                ", type='" + type + '\'' +
                ", crossOrigin=" + crossOrigin +
                '}';
    }
}
