package com.COSC4P02.PanoTour.entities;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

import java.util.Optional;

@Repository("Sections")
public class SectionDAO
{
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("PanoTour");

    public boolean addSection(Section section) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        boolean persisted = true;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.persist(section);
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

    public Optional<Section> getSectionBySid(int sid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM Section s WHERE s.uid = :UID";
        TypedQuery<Section> tq = em.createQuery(query, Section.class);
        tq.setParameter("SID", sid);

        Optional<Section> section = Optional.empty();
        try {
            section = Optional.of(tq.getSingleResult());
        } catch (NoResultException exception) {
            /*exception.printStackTrace();*/
        } finally {
            em.close();
        }
        return section;
    }

    public boolean deleteSection(Section section) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        boolean deleted = true;

        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.remove(em.contains(section) ? section : em.merge(section));
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