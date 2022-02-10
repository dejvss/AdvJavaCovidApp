package com.world.covid.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user_role",uniqueConstraints={@UniqueConstraint(columnNames = {"role_name"})})
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(name = "role_name", nullable = false)
    private String roleName;
    @Column(name = "role_status", nullable = false)
    private String roleStatus;
    @Column(name = "date_executed", nullable = false)
    private Date dateExecuted;
}
