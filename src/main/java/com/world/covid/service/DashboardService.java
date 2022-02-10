package com.world.covid.service;

import com.world.covid.entity.CountryEntity;
import com.world.covid.entity.CovidInformation;

import java.util.List;

public interface DashboardService {

    List<CountryEntity> getCountryList();

    List<CovidInformation> getCovidInfos();

    List<CovidInformation> getCovidInfoByCountry(String country);

    String saveCovidInfo(CovidInformation covidInformation);

    String updateCovidInfo(CovidInformation covidInformation);

    String deleteCovidInfo(CovidInformation covidInformation);
}
