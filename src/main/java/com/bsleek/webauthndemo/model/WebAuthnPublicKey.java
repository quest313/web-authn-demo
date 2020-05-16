package com.bsleek.webauthndemo.model;

import java.math.BigInteger;

public class WebAuthnPublicKey {

    private BigInteger id;
    private byte[] x;
    private byte[] y;
    private int alg;
    private int keyType;
    private int curveType;

    public WebAuthnPublicKey(BigInteger id, byte[] x, byte[] y, int alg, int keyType, int curveType) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.alg = alg;
        this.keyType = keyType;
        this.curveType = curveType;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public byte[] getX() {
        return x;
    }

    public void setX(byte[] x) {
        this.x = x;
    }

    public byte[] getY() {
        return y;
    }

    public void setY(byte[] y) {
        this.y = y;
    }

    public int getAlg() {
        return alg;
    }

    public void setAlg(int alg) {
        this.alg = alg;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    public int getCurveType() {
        return curveType;
    }

    public void setCurveType(int curveType) {
        this.curveType = curveType;
    }
}
