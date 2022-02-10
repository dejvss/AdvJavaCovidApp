package com.world.covid.endpoint;

import com.world.covid.entity.CovidInformation;
import com.world.covid.service.DashboardService;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
@Path("/world")
@Produces(MediaType.APPLICATION_JSON)
public class CovidApiController {

    @EJB
    DashboardService dashboardService;

    @Path("/covid")
    @GET
    public List<CovidInformation> getCovidInfoByCountry(@QueryParam("country") @NotNull String country) {
        List<CovidInformation> informationList = new ArrayList<>();
        System.out.println("API Country "+country);
        List<CovidInformation> covidInformations = dashboardService.getCovidInfoByCountry(country);
        for (CovidInformation information : covidInformations) {
            information.setId("");
            informationList.add(information);
        }
        return informationList;
    }
}
