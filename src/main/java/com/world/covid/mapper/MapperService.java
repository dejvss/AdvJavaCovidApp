package com.world.covid.mapper;

import com.world.covid.dto.UserDto;
import com.world.covid.entity.RoleEntity;
import com.world.covid.entity.UserEntity;
import com.world.covid.repository.RoleDao;
import com.world.covid.repository.UserDao;
import com.world.covid.util.GenUtil;
import com.world.covid.util.PasswordHash;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Stateless
public class MapperService {

    @EJB
    private GenUtil genUtil;
    @EJB
    private RoleDao roleDao;
    @Inject
    private UserDao userDao;

    public UserEntity mapUserDtoToUserEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(genUtil.getAlphaNumericString());
        userEntity.setName(userDto.getName());
        userEntity.setName(userDto.getSurename());
        userEntity.setPassword(PasswordHash.createHash(userDto.getPassword()));
        userEntity.setEmail(userDto.getEmail());
        userEntity.setDateExecuted(new Date());
        userEntity.setUserStatus("Active");
        //Get All Role
        List<RoleEntity> roleEntityList = roleDao.findAllByFilter();
        if (roleEntityList == null) {
            System.out.println("Exception::: Role got empty list");
            return new UserEntity();
        }
        userEntity.setRoleEntity(roleEntityList.stream().filter(r -> r.getRoleName().equalsIgnoreCase("USER")).findAny().orElse(null));
        return userEntity;
    }

}
