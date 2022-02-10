package com.world.covid.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tbl_country")
public class CountryEntity implements Serializable {

    @Id
    @Column(name = "country_iso_code", nullable = false)
    private String countryIsoCode;
    @Column(name = "country_name", nullable = false)
    private String countryName;
}
