package com.world.covid.repository;


import com.world.covid.entity.UserSessionEntity;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;

@Stateless
public class UserSessionDao implements Serializable {

    private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "covidPersistenceUnit";

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }

    public UserSessionEntity findUserSessionByUserName(String email) {
        EntityManager entityManager = getEntityManager();
        UserSessionEntity userSessionEntity = null;
        TypedQuery<UserSessionEntity> query = entityManager.createQuery("Select u from UserSessionEntity u where u.userEntity.email = ?1 and u.logoffTime is null", UserSessionEntity.class);
        query.setParameter(1, email);
        try {
            userSessionEntity = query.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        } finally {
            entityManager.close();
        }
        return userSessionEntity;
    }


    public UserSessionEntity findSessionBySessionId(Integer sessionId) {
        EntityManager entityManager = getEntityManager();
        UserSessionEntity userSessionEntity = null;
        try {
            userSessionEntity = entityManager.find(UserSessionEntity.class, sessionId);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        } finally {
            entityManager.close();
        }
        return userSessionEntity;
    }

    @Transactional
    public UserSessionEntity saveOrUpdateUserSession(UserSessionEntity userSessionEntity) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(userSessionEntity);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        } finally {
            manager.close();
        }
        return findUserSessionByUserName(userSessionEntity.getUserEntity().getEmail());
    }

    @Transactional
    public Boolean updateUserSession(Integer sessionId) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("update UserSessionEntity set logoffTime = ?1 where  sessionId = ?2 and logoffTime is null");
        query.setParameter(1, new Date());
        query.setParameter(2, sessionId);
        try {
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        } finally {
            entityManager.close();
        }
        return Boolean.TRUE;
    }
}
