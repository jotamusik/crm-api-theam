package com.agilemonkeys.crmapi.model.entity;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    // ToDo: for now it's a URL -> should be a photo
    @NotNull
    @URL
    private String photoUrl;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    private User creator;

    @OneToOne(fetch = FetchType.EAGER)
    private User lastModifier;
}
