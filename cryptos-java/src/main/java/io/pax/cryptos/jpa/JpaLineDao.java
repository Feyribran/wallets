package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.Line;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.jpa.JpaLine;
import io.pax.cryptos.domain.jpa.JpaWallet;

import javax.persistence.EntityManager;

/**
 * Created by AELION on 13/02/2018.
 */
public class JpaLineDao {

    JpaConnector connector = new JpaConnector();

    public Line createLine(JpaWallet wallet){
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        JpaLine line = new JpaLine();
        line.setWallet(wallet);
        em.persist(line);

        em.getTransaction().commit();
        em.close();
        System.out.println("User id: " + line.getId());
        return line;
    }


    public static void main(String[] args) {
        JpaLineDao daoLine = new JpaLineDao();
        JpaUserDao daoUser = new JpaUserDao();
        //daoUser.deleteByName("Arthur");

        User arthur = daoUser.createUser("Arthur");
        JpaWallet wallet = (JpaWallet) arthur.getWallets().get(0);
        //System.out.println(arthur);
        daoLine.createLine(wallet);
    }
}
