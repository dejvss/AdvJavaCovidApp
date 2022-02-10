package com.world.covid.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserInfoDto implements Serializable {

    private String userId;
    private String userName;
    private String userStatus;
    private Date logInTime;
    private Date lastLogInTime;
    private Date lastLogOutTime;
    private Date systemDate;
    private String roleName;
    private String postalCode;
}
