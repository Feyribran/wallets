package io.pax.cryptos.domain;

/**
 * Created by AELION on 12/02/2018.
 */
public class FullWallet extends SimpleWallet {

    SimpleUser user;

    //empty constructor with no real sense
    // Needed for Java EE bindings technology
    public FullWallet(){
        super();
    }

    public FullWallet(int id, String name, SimpleUser user) {
        super(id,name);
        this.user = user;
    }

    public SimpleUser getUser() {
        return this.user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

}
