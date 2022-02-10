package com.world.covid.bean;

import com.world.covid.dto.UserInfoDto;
import com.world.covid.entity.CovidInformation;
import com.world.covid.service.DashboardService;
import com.world.covid.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class CovidManagedBean implements Serializable {

    private CovidInformation covidInformation;
    private UserInfoDto userInfoDto;
    private List<CovidInformation> covidInformationList;
    private boolean isReadOnly;

    @EJB
    private UserService userService;
    @EJB
    private DashboardService dashboardService;

    public CovidManagedBean() {
        covidInformation = new CovidInformation();
        covidInformationList = new ArrayList<>();
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
        covidInformationList = dashboardService.getCovidInfos();
    }

    public void addCovidInformation() {
        FacesContext context = FacesContext.getCurrentInstance();
        String result = dashboardService.saveCovidInfo(covidInformation);
        if(result.equalsIgnoreCase("200")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Covid Information add successful.", "Covid Information add successful."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, result, result));
        }
    }

    public void editCovidInfo(CovidInformation covidInformation){
        this.covidInformation = covidInformation;
        this.isReadOnly = Boolean.TRUE;
    }

    public void updateCovidInformation() {
        FacesContext context = FacesContext.getCurrentInstance();
        String result = dashboardService.updateCovidInfo(covidInformation);
        if(result.equalsIgnoreCase("200")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Covid Information update successful.", "Covid Information update successful."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, result, result));
        }
    }

    public void deleteCovidInformation(CovidInformation covidInformation) {
        FacesContext context = FacesContext.getCurrentInstance();
        String result = dashboardService.deleteCovidInfo(covidInformation);
        if(result.equalsIgnoreCase("200")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Covid Information delete successful.", "Covid Information delete successful."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, result, result));
        }
    }

    public CovidManagedBean(CovidInformation covidInformation) {
        this.covidInformation = covidInformation;
    }

    public UserInfoDto getUserInfoDto() {
        return userInfoDto;
    }

    public void setUserInfoDto(UserInfoDto userInfoDto) {
        this.userInfoDto = userInfoDto;
    }

    public List<CovidInformation> getCovidInformationList() {
        return covidInformationList;
    }

    public void setCovidInformationList(List<CovidInformation> covidInformationList) {
        this.covidInformationList = covidInformationList;
    }

    public CovidInformation getCovidInformation() {
        return covidInformation;
    }

    public void setCovidInformation(CovidInformation covidInformation) {
        this.covidInformation = covidInformation;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public void setReadOnly(boolean readOnly) {
        isReadOnly = readOnly;
    }
}
