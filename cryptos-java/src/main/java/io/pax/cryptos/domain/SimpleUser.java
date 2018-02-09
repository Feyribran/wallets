package io.pax.cryptos.domain;

import java.util.List;

/**
 * Created by AELION on 07/02/2018.
 */
public class SimpleUser implements User {
    int id;
    String name;

    public SimpleUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public List<Wallet> getWallets() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
