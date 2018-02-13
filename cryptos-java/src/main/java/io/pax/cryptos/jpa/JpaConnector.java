package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.jpa.JpaUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Wrap factory
 */
public class JpaConnector {
    EntityManagerFactory factory;

    void connect() {
        if (this.factory == null) {
            this.factory = Persistence.createEntityManagerFactory("cryptos");
        }
    }

    public EntityManager createEntityManager() {
        // if already connected, do nothing
        this.connect();
        return factory.createEntityManager();
    }

    public void close() {
        this.factory.close();
    }


/**
 * Tests
 */
    public static void main(String[] args) {
        JpaConnector connector = new JpaConnector();
        EntityManager em = connector.createEntityManager();

        JpaUser jean = new JpaUser();
        jean.setName("Jean");
        JpaUser jack = new JpaUser();
        jack.setName("Jack");
        JpaUser john = new JpaUser();
        john.setName("John");
        JpaUser jackie = new JpaUser();
        jackie.setName("Jackie");
        JpaUser jasper = new JpaUser();
        jasper.setName("Jasper");
        JpaUser jules = new JpaUser();
        jules.setName("Jules");

        // open the box
        em.getTransaction().begin();

        em.persist(jean);
        em.persist(jack);
        em.persist(jackie);
        em.persist(john);
        em.persist(jules);
        em.persist(jasper);
        em.getTransaction().commit();

        // close the box
        em.close();
        connector.close();
    }
}
