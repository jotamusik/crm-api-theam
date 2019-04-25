package com.agilemonkeys.crmapi.model.request;

import lombok.Data;

@Data
public class UpdateCustomerRequest {

    private String name;

    private String surname;

    private String photoUrl;
}
