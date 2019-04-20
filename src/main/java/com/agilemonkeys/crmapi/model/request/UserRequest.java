package com.agilemonkeys.crmapi.model.request;


import com.agilemonkeys.crmapi.model.enums.Roles;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    private Roles role;
}
