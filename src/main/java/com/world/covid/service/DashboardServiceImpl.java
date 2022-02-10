package com.world.covid.service;

import com.world.covid.entity.CountryEntity;
import com.world.covid.entity.CovidInformation;
import com.world.covid.repository.CountryDao;
import com.world.covid.repository.CovidInformationDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DashboardServiceImpl implements DashboardService{

    @Inject
    CovidInformationDao covidInformationDao;
    @Inject
    CountryDao countryDao;

    @Override
    public List<CountryEntity> getCountryList() {
        return countryDao.findAllOfCountry();
    }

    @Override
    public List<CovidInformation> getCovidInfos() {
        return covidInformationDao.findAllCovidInfo();
    }

    @Override
    public List<CovidInformation> getCovidInfoByCountry(String country) {
        if(null == country || country.isEmpty()){
            System.out.println("Parameter value is empty or null.");
            return new ArrayList<>();
        }
        return covidInformationDao.findAllCovidInfoByCountry(country);
    }

    @Override
    public String saveCovidInfo(CovidInformation covidInformation) {
        covidInformationDao.saveCovidInfo(covidInformation);
        return "200";
    }

    @Override
    public String updateCovidInfo(CovidInformation covidInformation) {
        covidInformationDao.updateCovidInfo(covidInformation);
        return "200";
    }

    @Override
    public String deleteCovidInfo(CovidInformation covidInformation) {
        covidInformationDao.deleteCountry(covidInformation);
        return "200";
    }
}
