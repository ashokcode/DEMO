package com.jpa.csv.parse;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TransactionFacade {
    private final EntityManager em;

    public static void main(String[] args) {
            EntityManager em = Persistence.createEntityManagerFactory((String)"Model1-Outside").createEntityManager();
            //EntityManagerFactory emf = Persistence.createEntityManagerFactory((String)"Model-1");
           // em = emf.createEntityManager();
            TransactionSuccess success = new TransactionSuccess();
            success.setAmount(Long.valueOf(5000));
            success.setFilename("sample.csv");
            success.setOrderingcurrency("AED");
            success.setTocurrency("USD");
            success.setUid("1000008");
            success.setTransactiontime(new Timestamp(new Date().getTime()));
            em.persist((Object)success);
            em.getTransaction().begin();
            em.flush();
            em.getTransaction().commit();
            em.close();
            List<TransactionSuccess> successlist = new TransactionFacade().getTransactionSuccessFindAll();
            for (TransactionSuccess sucss : successlist) {
                System.out.println(sucss.getUid());
            }
        }
    public TransactionFacade() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Model1-Outside");
        em = emf.createEntityManager();
    }

    /**
     * All changes that have been made to the managed entities in the
     * persistence context are applied to the database and committed.
     */
    public void commitTransaction() {
        final EntityTransaction entityTransaction = em.getTransaction();
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }
        entityTransaction.commit();
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public <T> T persistEntity(T entity) {
        em.persist(entity);
        commitTransaction();
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        entity = em.merge(entity);
        commitTransaction();
        return entity;
    }

    public void removeTransactionSuccess(TransactionSuccess transactionSuccess) {
        transactionSuccess = em.find(TransactionSuccess.class, transactionSuccess.getUid());
        em.remove(transactionSuccess);
        commitTransaction();
    }

    /** <code>select o from TransactionSuccess o</code> */
    public List<TransactionSuccess> getTransactionSuccessFindAll() {
        return em.createNamedQuery("TransactionSuccess.findAll", TransactionSuccess.class).getResultList();
    }

    public void removeTransactionFailed(TransactionFailed transactionFailed) {
        transactionFailed = em.find(TransactionFailed.class, transactionFailed.getUid());
        em.remove(transactionFailed);
        commitTransaction();
    }

    /** <code>select o from TransactionFailed o</code> */
    public List<TransactionFailed> getTransactionFailedFindAll() {
        return em.createNamedQuery("TransactionFailed.findAll", TransactionFailed.class).getResultList();
    }
    
    /** <code>select o from TransactionCounts o</code> */
    public List<TransactionCounts> getTransactionCountsFindAll() {
        return em.createNamedQuery("TransactionCounts.findAll", TransactionCounts.class).getResultList();
    }

    /**
     * This is the method to refresh the table data
     */
    public void refreshTabeData() {
       
        commitTransaction();
    }
}
