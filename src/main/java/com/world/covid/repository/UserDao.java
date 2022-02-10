package com.world.covid.repository;

import com.world.covid.entity.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;

@Stateless
public class UserDao implements Serializable {

    private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "covidPersistenceUnit";

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }

    @Transactional
    public UserEntity saveUser(UserEntity userEntity) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(userEntity);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception ::" + ex.getMessage());
        }finally {
            manager.close();
        }
        return userEntity;
    }

    public UserEntity findUserByEmail(String email) {
        EntityManager entityManager = getEntityManager();
        UserEntity userEntity = null;
        TypedQuery<UserEntity> query = entityManager.createQuery("Select u from UserEntity u where u.email = ?1", UserEntity.class);
        query.setParameter(1, email);
        try {
            userEntity = query.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }finally {
            entityManager.close();
        }
        return userEntity;
    }
}
