package com.world.covid.bean;

import com.world.covid.dto.UserInfoDto;
import com.world.covid.service.UserService;
import org.javatuples.Pair;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginManagedBean implements Serializable {

    private String email;
    private String password;
    public static String AUTH_KEY = "";


    @EJB
    private UserService userService;

    public LoginManagedBean() {
    }

    public void checkAuth() {
        FacesContext context = FacesContext.getCurrentInstance();
        Pair<String, UserInfoDto> response = userService.userLogin(email, password);
        if (response != null) {
            if (response.getValue0() != null) {
                context.getExternalContext().getSessionMap().put("sessionID", response.getValue0());
                context.getExternalContext().getSessionMap().put("userInfo", response.getValue1());
                context.getExternalContext().getSessionMap().put(AUTH_KEY, response.getValue1().getUserName());
                FacesContext
                        .getCurrentInstance()
                        .getApplication()
                        .getNavigationHandler()
                        .handleNavigation(FacesContext.getCurrentInstance(),
                                "null", "/dashboard-user.xhtml?faces-redirect=true");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication Failed!", "Enter valid data."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fetal Error!", "Response is null."));
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
