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
@Table(name = "tbl_user_session")
public class UserSessionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id", nullable = false)
    private Integer sessionId;
    @ManyToOne
    private UserEntity userEntity;
    @Column(name = "logon_time")
    private Date logonTime;
    @Column(name = "logoff_time")
    private Date logoffTime;
}
