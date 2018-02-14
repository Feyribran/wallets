package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.jpa.JpaUser;
import io.pax.cryptos.domain.jpa.JpaWallet;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */
public class JpaUserDao {

    JpaConnector connector = new JpaConnector();

    public JpaUser createUser(String name){
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        JpaUser user = new JpaUser();
        user.setName(name);
        em.persist(user);

        JpaWallet defaultWallet = new JpaWallet();
        defaultWallet.setName(name + "'s wallet");
        em.persist(defaultWallet);
        user.getWallets().add(defaultWallet);

        em.getTransaction().commit();
        em.close();
        System.out.println("User id: " + user.getId());
        return user;
    }

    public JpaUser find(int id){
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();
        JpaUser user = em.find(JpaUser.class, id);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public JpaUser findByName(String name){
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        //JPQL : Java Persistence Query Language
        TypedQuery<JpaUser> query = em.createQuery("SELECT u FROM JpaUser u WHERE u.name = :pName",JpaUser.class);

        query.setParameter("pName",name);
        List<JpaUser> users = query.getResultList();

        em.getTransaction().commit();
        em.close();
        if (users.size() > 0){
            return users.get(0);
        } else {
            System.out.println("No " + name + " in your dtb !");
            return null;
        }
    }

    public void deleteByName(String name){
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        //JPQL : Java Persistence Query Language
        TypedQuery<JpaUser> query = em.createQuery("SELECT u FROM JpaUser u WHERE u.name = :pName",JpaUser.class);

        query.setParameter("pName",name);
        List<JpaUser> users = query.getResultList();


        for (User user: users){
            em.remove(user);
        }

        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args) {
        JpaUserDao dao = new JpaUserDao();

        dao.createUser("Arthur");


        // end of a very long program
        dao.connector.close();
    }

}
