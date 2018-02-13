package io.pax.cryptos.domain.jpa;

import io.pax.cryptos.domain.Line;

import javax.persistence.*;

/**
 * Created by AELION on 13/02/2018.
 */
@Entity
public class JpaLine implements Line {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String symbol;
    double quantity;

    @ManyToOne
    JpaWallet wallet;

    public void setId(int id) {
        this.id = id;
    }

    public void setWallet(JpaWallet wallet) {
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public JpaWallet getWallet() {
        return wallet;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public double getQuantity() {
        return this.quantity;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
