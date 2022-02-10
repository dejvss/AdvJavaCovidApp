package com.world.covid.bean;

import com.world.covid.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class LogoutManaggedBean implements Serializable {

    @Inject
    private UserService userService;

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        String sessionID = (String) context.getExternalContext().getSessionMap().get("sessionID");
        userService.logOutSystem(Integer.parseInt(sessionID));
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("/worldcovid/login.xhtml");
            context.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
