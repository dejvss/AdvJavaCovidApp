package com.world.covid.repository;

import com.world.covid.entity.RoleEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class RoleDao {

    private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "covidPersistenceUnit";

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }

    public List<RoleEntity> findAllByFilter() {
        EntityManager entityManager = getEntityManager();
        List<RoleEntity> roleEntities = null;
        TypedQuery<RoleEntity> query = entityManager.createQuery("Select c from RoleEntity c", RoleEntity.class);
        try {
            roleEntities = query.getResultList();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }finally {
            entityManager.close();
        }
        return roleEntities;
    }
}
