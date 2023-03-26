package com.example.demo5;

public final class Help<A, B> {
    private final A v1;
    private final B v2;
    public Help(A v1, B v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
    public A get1() {
        return v1;
    }

    public B get2() {
        return v2;
    }
}