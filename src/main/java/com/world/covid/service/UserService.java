package com.world.covid.service;

import com.world.covid.dto.UserInfoDto;
import com.world.covid.entity.CovidInformation;
import org.javatuples.Pair;

import java.io.Serializable;

public interface UserService extends Serializable {

    Boolean isExistByUserName(String userName);

    Pair<String, UserInfoDto> userLogin(String userName, String password);

    void logOutSystem(Integer sessionId) ;

    String saveCovidInformation(CovidInformation covidInformation);

}
