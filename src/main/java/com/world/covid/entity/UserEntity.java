package com.world.covid.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user",uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
public class UserEntity implements Serializable {

    @Id
    private String userId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surename", nullable = false)
    private String surename;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "user_status", nullable = false, length = 10)
    private String userStatus;
    @Column(name = "date_executed", nullable = false)
    private Date dateExecuted;
    @OneToOne
    private RoleEntity roleEntity;
    @OneToMany( mappedBy = "userEntity",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserSessionEntity> userSessions = new HashSet<UserSessionEntity>(0);
}
