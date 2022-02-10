package com.world.covid.service;

import com.world.covid.entity.CountryEntity;

import java.util.List;

public interface CountryService {

    String saveCountry(CountryEntity countryEntity);
    String updateCountry(CountryEntity countryEntity);
    String deleteCountry(CountryEntity countryEntity);
    List<CountryEntity> getAllCountry();
}
