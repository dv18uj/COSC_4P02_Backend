package com.COSC4P02.PanoTour.entities;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

import java.util.Optional;
import java.util.List;
import java.util.Collections;
@Repository("Sections")
public class SectionDAO {
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
    //Return a section with the given LID
    public Optional<Section> getSectionBySid(int sid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM Section s WHERE s.sid = :SID";
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

    // Returns the list of sections with a common LID
    public List<Section> getSectionsFromLid(int lid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM Section s WHERE s.lid = :LID";
        TypedQuery<Section> typedQuery = em.createQuery(query, Section.class);
        typedQuery.setParameter("LID", lid);
        List<Section> section = Collections.emptyList();

        try{
            section = typedQuery.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
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