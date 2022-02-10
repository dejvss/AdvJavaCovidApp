package com.world.covid.service;

import com.world.covid.entity.CountryEntity;
import com.world.covid.repository.CountryDao;
import org.apache.http.HttpStatus;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CountryServiceImpl implements CountryService{

    @Inject
    CountryDao countryDao;

    @Override
    public String saveCountry(CountryEntity countryEntity) {
        countryDao.saveCountry(countryEntity);
        return "200";
    }

    @Override
    public String updateCountry(CountryEntity countryEntity) {
        countryDao.updateCountry(countryEntity);
        return "200";
    }

    @Override
    public String deleteCountry(CountryEntity countryEntity) {
        countryDao.deleteCountry(countryEntity);
        return "200";
    }

    @Override
    public List<CountryEntity> getAllCountry() {
        return countryDao.findAllOfCountry();
    }
}
