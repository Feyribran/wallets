package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.Wallet;
import io.pax.cryptos.domain.jpa.JpaLine;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */
public class JpaWalletDao {

    JpaConnector connector = new JpaConnector();

    public List<JpaLine> fetchLines(Wallet wallet){
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        //JPQL : Java Persistence Query Language
        TypedQuery<JpaLine> query = em.createQuery("SELECT w FROM JpaWallet w WHERE w.name = :pName",JpaLine.class);

        query.setParameter("pName", wallet);
        List<JpaLine> lines = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return lines;
    }
}
