package com.world.covid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private String name;
    private String surename;
    private String email;
    private String userName;
    private String password;
}
