package io.pax.cryptos.domain;

/**
 * Created by AELION on 06/02/2018.
 */
public interface Wallet {
    User getUser();
    String getName();
    int getId();
}
