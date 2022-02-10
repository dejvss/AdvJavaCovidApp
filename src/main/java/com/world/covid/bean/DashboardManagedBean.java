package com.world.covid.bean;

import com.world.covid.entity.CountryEntity;
import com.world.covid.entity.CovidInformation;
import com.world.covid.service.DashboardService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class DashboardManagedBean implements Serializable {

    private String countryName;
    private List<CovidInformation> covidInformations = null;
    private List<CountryEntity> countryList = null;

    @EJB
    DashboardService dashboardService;


    public DashboardManagedBean() {
        covidInformations = new ArrayList<>();
        countryList = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        covidInformations = dashboardService.getCovidInfos();
        countryList = dashboardService.getCountryList();
    }

    public void onCountryChange() {
        if (countryName == null) {
            covidInformations = new ArrayList<>();
        } else {
            covidInformations = dashboardService.getCovidInfoByCountry(countryName);
        }
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public DashboardService getDashboardService() {
        return dashboardService;
    }

    public void setDashboardService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    public List<CovidInformation> getCovidInformations() {
        return covidInformations;
    }

    public void setCovidInformations(List<CovidInformation> covidInformations) {
        this.covidInformations = covidInformations;
    }

    public List<CountryEntity> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryEntity> countryList) {
        this.countryList = countryList;
    }
}
