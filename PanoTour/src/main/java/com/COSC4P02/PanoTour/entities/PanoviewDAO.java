package com.COSC4P02.PanoTour.entities;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Optional;

@Repository("Panoview")
public class PanoviewDAO {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("PanoTour");

    public boolean addPanoview(Panoview panoview) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        boolean persisted = true;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.persist(panoview);
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

    public Optional<Panoview> getPanoviewByPid(int pid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT p FROM Panoview p WHERE p.pid = :PID";
        TypedQuery<Panoview> tq = em.createQuery(query, Panoview.class);
        tq.setParameter("PID", pid);

        Optional<Panoview> panoview = Optional.empty();
        try {
            panoview = Optional.of(tq.getSingleResult());
        } catch (NoResultException exception) {
            /*exception.printStackTrace();*/
        } finally {
            em.close();
        }
        return panoview;
    }

    public boolean deletePanoview(Panoview panoview) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        boolean deleted = true;

        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.remove(em.contains(panoview) ? panoview : em.merge(panoview));
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
}