package io.pax.cryptos.domain;

/**
 * Created by AELION on 06/02/2018.
 */
public interface Wallet {
    default User getUser(){
        return null;
    }
    String getName();
    int getId();
}
