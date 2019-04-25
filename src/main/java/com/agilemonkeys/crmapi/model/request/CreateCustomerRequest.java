package com.agilemonkeys.crmapi.model.request;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateCustomerRequest {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String photoUrl;

}
