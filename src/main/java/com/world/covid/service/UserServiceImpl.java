package com.world.covid.service;

import com.world.covid.dto.UserInfoDto;
import com.world.covid.entity.CovidInformation;
import com.world.covid.entity.UserEntity;
import com.world.covid.entity.UserSessionEntity;
import com.world.covid.mapper.MapperService;
import com.world.covid.repository.UserDao;
import com.world.covid.repository.UserSessionDao;
import com.world.covid.util.PasswordHash;
import org.javatuples.Pair;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;
    @Inject
    private UserSessionDao userSessionDao;
    @Inject
    private MapperService mapperService;


    @Override
    public Boolean isExistByUserName(String email) {
        if (userDao.findUserByEmail(email) == null) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Pair<String, UserInfoDto> userLogin(String email, String password) {
        List<String> errors = new ArrayList<>();
        String sessionId = null;
        UserEntity userEntity;
        UserSessionEntity session = null;
        UserInfoDto userInfoDto = null;
        boolean hasRight = false;
        boolean isCredentialMatch = false;
        if (email != null && password != null) {
            userEntity = userDao.findUserByEmail(email);
            if (userEntity != null) {
                if (userEntity.getRoleEntity() != null) {
                    hasRight = Boolean.TRUE;
                }
                if (hasRight) {
                    if (PasswordHash.validatePassword(password, userEntity.getPassword())) {
                        isCredentialMatch = Boolean.TRUE;
                    } else {
                        errors.add("Credential not matched.");
                    }
                    if (isCredentialMatch) {
                        userInfoDto = new UserInfoDto();
                        userInfoDto.setRoleName(userEntity.getRoleEntity().getRoleName());
                        userInfoDto.setUserId(userEntity.getUserId());
                        userInfoDto.setUserName(userEntity.getEmail());
                        userInfoDto.setUserStatus(userEntity.getUserStatus());
                        session = userSessionDao.findUserSessionByUserName(userEntity.getEmail());
                        if (session != null) {
                            userInfoDto.setLastLogInTime(session.getLogonTime());
                            session.setLogonTime(new Date());
                        } else {
                            session = new UserSessionEntity();
                            session.setUserEntity(userEntity);
                            session.setLogonTime(new Date());
                        }
                        try {
                            UserSessionEntity sessionEntity = userSessionDao.saveOrUpdateUserSession(session);
                            sessionId = String.valueOf(sessionEntity.getSessionId());
                        } catch (Exception ex) {
                            errors.add(ex.getMessage());
                            System.out.println("Exception " + ex.getMessage());
                        }
                    } else {
                        errors.add("Credential not matched.");
                    }
                } else {
                    errors.add("Permission denied.");
                }
            } else {
                errors.add("User not found");
            }
        }
        return new Pair<>(sessionId, userInfoDto);
    }

    @Override
    public void logOutSystem(Integer sessionId) {
        userSessionDao.updateUserSession(sessionId);
    }

    @Override
    public String saveCovidInformation(CovidInformation covidInformation) {
        return null;
    }
}
