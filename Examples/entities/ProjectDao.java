package com.project.TalonMillwork.entities;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("Projects")
public class ProjectDao {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("TalonMillwork");

    public List<Project> getAllProjects() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT p FROM Project p WHERE p.pid IS NOT NULL";
        TypedQuery<Project> typedQuery = em.createQuery(query, Project.class);
        List<Project> projects = Collections.emptyList();

        try{
            projects = typedQuery.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            em.close();
        }
        return projects;
    }

    public boolean addProject(Project project) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        boolean persisted = true;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.persist(project);
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

    public Optional<Project> getProjectByPid(int pid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT p FROM Project p WHERE p.pid = :PID";
        TypedQuery<Project> tq = em.createQuery(query, Project.class);
        tq.setParameter("PID", pid);

        Optional<Project> project = Optional.empty();
        try {
            project = Optional.of(tq.getSingleResult());
        } catch (NoResultException exception) {
            /*exception.printStackTrace();*/
        } finally {
            em.close();
        }
        return project;
    }

    public boolean deleteProject(Project project) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        boolean deleted = true;

        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            // em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.remove(em.contains(project) ? project : em.merge(project));
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
