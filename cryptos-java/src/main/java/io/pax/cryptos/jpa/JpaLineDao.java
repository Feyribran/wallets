package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.jpa.JpaLine;
import io.pax.cryptos.domain.jpa.JpaUser;
import io.pax.cryptos.domain.jpa.JpaWallet;

import javax.persistence.EntityManager;

/**
 * Created by AELION on 14/02/2018.
 */
public class JpaLineDao {
    JpaConnector connector = new JpaConnector();

    public static void main(String[] args) {

        JpaUserDao userDao = new JpaUserDao();
        JpaUser kenny = userDao.createUser("Kenny");
        JpaWallet wallet = kenny.getWallets().get(0);

        JpaLineDao lineDao = new JpaLineDao();
        EntityManager em = lineDao.connector.createEntityManager();

        em.getTransaction().begin();

        wallet = em.merge(wallet);

        JpaLine lineBtc = new JpaLine();
        lineBtc.setQuantity(12.34);
        lineBtc.setSymbol("BTC");
        lineBtc.setWallet(wallet);
        em.persist(lineBtc); //pour enregistrer dans la base de données

        JpaLine lineXmr = new JpaLine();
        lineXmr.setSymbol("XMR");
        lineXmr.setQuantity(1045);
        lineXmr.setWallet(wallet);
        em.persist(lineXmr);

        System.out.println(lineXmr.getWallet());

        // Commit and close
        em.getTransaction().commit();
        em.close();
        lineDao.connector.close();
        //userDao.connector.close();


    }
}
