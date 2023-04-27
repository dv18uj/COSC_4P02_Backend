package com.COSC4P02.PanoTour.entities;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("Users")
public class UserDAO
{
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("PanoTour");

    public boolean addUser(User user) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        boolean persisted = true;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.persist(user);
            entityTransaction.commit();
        } catch (Exception exception) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
                persisted = false;
            }
        } finally {
            em.close();
        }
        return persisted;
    }

    public Optional<User> getUserByUid(int uid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT u FROM User u WHERE u.uid = :UID";
        TypedQuery<User> tq = em.createQuery(query, User.class);
        tq.setParameter("UID", uid);

        Optional<User> user = Optional.empty();
        try {
            user = Optional.of(tq.getSingleResult());
        } catch (NoResultException exception) {
            /*exception.printStackTrace();*/
        } finally {
            em.close();
        }
        return user;
    }

    public Optional<User> getUserByName(String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT u FROM User u WHERE u.name = :NAME";
        TypedQuery<User> tq = em.createQuery(query, User.class);
        tq.setParameter("NAME", name);

        Optional<User> user = Optional.empty();
        try {
            user = Optional.of(tq.getSingleResult());
        } catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            em.close();
        }
        return user;
    }

    public boolean deleteUser(User user) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        boolean deleted = true;

        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.remove(em.contains(user) ? user : em.merge(user));
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
                deleted = false;
            }
        } finally {
            em.close();
        }
        return deleted;
    }

    public List<User> getAllUsers() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT u FROM User u WHERE u.uid IS NOT NULL";
        TypedQuery<User> typedQuery = em.createQuery(query, User.class);
        List<User> users = Collections.emptyList();

        try{
            users = typedQuery.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            em.close();
        }
        return users;
    }
}
