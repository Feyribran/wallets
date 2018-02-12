package io.pax.cryptos.domain;

/**
 * Created by AELION on 06/02/2018.
 */
public class SimpleWallet implements Wallet {
    int id;
    String name;
    User user;

    public SimpleWallet(){

    }

    public SimpleWallet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public User getUser() {
        return null;
    }

    public void setUser(User user){
        this.user = user;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
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
