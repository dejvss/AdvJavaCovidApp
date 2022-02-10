package com.world.covid.repository;

import com.world.covid.entity.CountryEntity;
import com.world.covid.entity.CovidInformation;

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
public class CovidInformationDao implements Serializable {

    private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "covidPersistenceUnit";

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }

    public List<CovidInformation> findAllCovidInfoByCountry(String country) {
        EntityManager entityManager = getEntityManager();
        List<CovidInformation> informationList = new ArrayList<>();
        TypedQuery<CovidInformation> query = entityManager.createQuery("Select u from CovidInformation u where u.location = ?1", CovidInformation.class);
        query.setParameter(1, country);
        try {
            informationList = query.getResultList();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }finally {
            entityManager.close();
        }
        return informationList;
    }

    public List<CovidInformation> findAllCovidInfo() {
        EntityManager entityManager = getEntityManager();
        List<CovidInformation> informationList = new ArrayList<>();
        TypedQuery<CovidInformation> query = entityManager.createQuery("Select u from CovidInformation u ", CovidInformation.class);
        try {
            informationList = query.getResultList();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }finally {
            entityManager.close();
        }
        return informationList;
    }


    @Transactional
    public CovidInformation saveCovidInfo(CovidInformation covidInformation) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(covidInformation);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception ::" + ex.getMessage());
        }finally {
            manager.close();
        }
        return covidInformation;
    }

    @Transactional
    public CovidInformation updateCovidInfo(CovidInformation covidInformation) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.merge(covidInformation);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception ::" + ex.getMessage());
        }finally {
            manager.close();
        }
        return covidInformation;
    }

    @Transactional
    public CovidInformation deleteCountry(CovidInformation covidInformation) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            System.out.println("Delete query "+covidInformation.toString());
            Object covid = manager.merge(covidInformation);
            manager.remove(covid);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception ::" + ex.getMessage());
        }finally {
            manager.close();
        }
        return covidInformation;
    }
}
