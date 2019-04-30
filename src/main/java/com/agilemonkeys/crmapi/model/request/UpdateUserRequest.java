package com.agilemonkeys.crmapi.model.request;

import com.agilemonkeys.crmapi.model.enums.Roles;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUserRequest {

    private String password;
    private List<Roles> roles;

}
