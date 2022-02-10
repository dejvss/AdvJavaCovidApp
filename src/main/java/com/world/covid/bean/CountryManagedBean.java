package com.world.covid.bean;

import com.world.covid.dto.UserInfoDto;
import com.world.covid.entity.CountryEntity;
import com.world.covid.service.CountryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class CountryManagedBean {

    private UserInfoDto userInfoDto;
    private CountryEntity countryEntity;
    private List<CountryEntity> countryEntityList;
    private boolean isReadOnly;

    @EJB
    CountryService countryService;

    public CountryManagedBean() {
        countryEntity = new CountryEntity();
        countryEntityList = new ArrayList<>();
    }

    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        String sessionID = (String) context.getExternalContext().getSessionMap().get("sessionID");
        userInfoDto = (UserInfoDto)context.getExternalContext().getSessionMap().get("userInfo");
        if (sessionID == null) {
            FacesContext
                    .getCurrentInstance()
                    .getApplication()
                    .getNavigationHandler()
                    .handleNavigation(FacesContext.getCurrentInstance(),
                            "null", "/access-denied.xhtml?faces-redirect=true");
        }
        countryEntityList = countryService.getAllCountry();
    }

    public void addCountryInformation() {
        FacesContext context = FacesContext.getCurrentInstance();
        String result = countryService.saveCountry(countryEntity);
        if(result.equalsIgnoreCase("200")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Covid Information add successful.", "Covid Information add successful."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, result, result));
        }
    }

    public void updateCountryInformation() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("update Object "+countryEntity.toString());
        String result = countryService.updateCountry(countryEntity);
        if(result.equalsIgnoreCase("200")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Covid Information update successful.", "Covid Information update successful."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, result, result));
        }
    }

    public void editCountryInfo(CountryEntity country) {
        System.out.println("Edit Object "+country.toString());
        this.countryEntity = country;
        isReadOnly = Boolean.TRUE;
    }

    public void deleteCountryInfo(CountryEntity countryEntity) {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("Delete Object "+countryEntity.toString());
        String result = countryService.deleteCountry(countryEntity);
        if(result.equalsIgnoreCase("200")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Covid Information delete successful.", "Covid Information delete successful."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, result, result));
        }
    }


    public UserInfoDto getUserInfoDto() {
        return userInfoDto;
    }

    public void setUserInfoDto(UserInfoDto userInfoDto) {
        this.userInfoDto = userInfoDto;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
    }

    public List<CountryEntity> getCountryEntityList() {
        return countryEntityList;
    }

    public void setCountryEntityList(List<CountryEntity> countryEntityList) {
        this.countryEntityList = countryEntityList;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public void setReadOnly(boolean readOnly) {
        isReadOnly = readOnly;
    }
}
