package com.world.covid.repository;

import com.world.covid.entity.CountryEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CountryDao implements Serializable {

    private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "covidPersistenceUnit";

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }

    public List<CountryEntity> findAllOfCountry() {
        EntityManager entityManager = getEntityManager();
        List<CountryEntity> countryEntities = new ArrayList<>();
        TypedQuery<CountryEntity> query = entityManager.createQuery("Select c from CountryEntity c", CountryEntity.class);
        try {
            countryEntities = query.getResultList();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }finally {
            entityManager.close();
        }
        return countryEntities;
    }

    @Transactional
    public CountryEntity saveCountry(CountryEntity countryEntity) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(countryEntity);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception ::" + ex.getMessage());
        }finally {
            manager.close();
        }
        return countryEntity;
    }

    @Transactional
    public CountryEntity updateCountry(CountryEntity countryEntity) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.merge(countryEntity);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception ::" + ex.getMessage());
        }finally {
            manager.close();
        }
        return countryEntity;
    }

    @Transactional
    public CountryEntity deleteCountry(CountryEntity countryEntity) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            System.out.println("Delete query "+countryEntity.toString());
            Object country = manager.merge(countryEntity);
            manager.remove(country);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception ::" + ex.getMessage());
        }finally {
            manager.close();
        }
        return countryEntity;
    }
}
