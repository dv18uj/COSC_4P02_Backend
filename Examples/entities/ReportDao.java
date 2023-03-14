package com.project.TalonMillwork.entities;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("Reports")
public class ReportDao {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("TalonMillwork");

    public List<Report> getAllReports() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT r FROM Report r WHERE r.rid IS NOT NULL";
        TypedQuery<Report> typedQuery = em.createQuery(query, Report.class);
        List<Report> reports = Collections.emptyList();

        try{
            reports = typedQuery.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            em.close();
        }
        return reports;
    }

    public boolean addReport(Report report) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        boolean persisted = true;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.persist(report);
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

    public Optional<Report> getReportByRid(int rid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT r FROM Report r WHERE r.rid = :RID";
        TypedQuery<Report> tq = em.createQuery(query, Report.class);
        tq.setParameter("RID", rid);

        Optional<Report> report = Optional.empty();
        try {
            report = Optional.of(tq.getSingleResult());
        } catch (NoResultException exception) {
            /*exception.printStackTrace();*/
        } finally {
            em.close();
        }
        return report;
    }

    public boolean deleteReport(Report report) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        boolean deleted = true;

        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            // em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.remove(em.contains(report) ? report : em.merge(report));
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

    public List<Report> getProjectReports(int pid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT r FROM Report r WHERE r.pid="+pid;
        TypedQuery<Report> typedQuery = em.createQuery(query, Report.class);
        List<Report> reports = Collections.emptyList();

        try{
            reports = typedQuery.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            em.close();
        }
        return reports;
    }

    public boolean addSubtrade(int rid, String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        Query query = em.createNativeQuery("INSERT INTO report_subtrade(rid, name) VALUES(?,?)")
                .setParameter(1, rid)
                .setParameter(2, name);
        boolean persisted = true;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            query.executeUpdate();
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

    /**
     * This method returns a list of user ids of the sub-trades associated with the report
     * @param rid is the report id
     * @return list of user ids
     */
    public List<String> getReportSubtradeNames(int rid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query query = em.createNativeQuery("SELECT name FROM report_subtrade WHERE report_subtrade.rid=?")
                .setParameter(1, rid);
        List<String> subtrades = Collections.emptyList();
        try{
            subtrades = query.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            em.close();
        }
        return subtrades;
    }

    public boolean addInstaller(int rid, String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        Query query = em.createNativeQuery("INSERT INTO report_installer(rid, name) VALUES(?,?)")
                .setParameter(1, rid)
                .setParameter(2, name);
        boolean persisted = true;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            query.executeUpdate();
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

    /**
     * This method returns a list of user ids of the installers associated with the report
     * @param rid is the report id
     * @return list of user ids
     */
    public List<String> getReportInstallerNames(int rid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query query = em.createNativeQuery("SELECT name FROM report_installer WHERE report_installer.rid=?")
                .setParameter(1, rid);
        List<String> installerIds = Collections.emptyList();
        try{
            installerIds = query.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            em.close();
        }
        return installerIds;
    }

    public boolean saveReportImage(ReportImage reportImage) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        boolean persisted = true;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.persist(reportImage);
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

    public List<Report_preImage> getReportPreImages(int rid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query;
        query = "SELECT rp FROM Report_preimage rp WHERE rp.rid=" + rid;
        TypedQuery<Report_preImage> typedQuery = em.createQuery(query, Report_preImage.class);
        List<Report_preImage> reportPostImages = Collections.emptyList();
        try{
            reportPostImages = typedQuery.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            em.close();
        }
        return reportPostImages;
    }

    public List<Report_postImage> getReportPostImages(int rid) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query;
        query = "SELECT rp FROM Report_postimage rp WHERE rp.rid=" + rid;
        TypedQuery<Report_postImage> typedQuery = em.createQuery(query, Report_postImage.class);
        List<Report_postImage> reportPostImages = Collections.emptyList();
        try{
            reportPostImages = typedQuery.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            em.close();
        }
        return reportPostImages;
    }

    public Optional<Report_preImage> getReportPreImage(int imgId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT rp FROM Report_preImage rp WHERE rp.imgId="+imgId;
        TypedQuery<Report_preImage> typedQuery = em.createQuery(query, Report_preImage.class);
        Optional<Report_preImage> reportImage = Optional.empty();
        try{
            reportImage = Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            em.close();
        }
        return reportImage;
    }
    public Optional<Report_postImage> getReportPostImage(int imgId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT rp FROM Report_postImage rp WHERE rp.imgId="+imgId;
        TypedQuery<Report_postImage> typedQuery = em.createQuery(query, Report_postImage.class);
        Optional<Report_postImage> reportImage = Optional.empty();
        try{
            reportImage = Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            em.close();
        }
        return reportImage;
    }
}
