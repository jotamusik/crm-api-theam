package com.agilemonkeys.crmapi.service;

import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.enums.Roles;

public interface UserSecurityService {

    User getCurrentUser();

    boolean isCurrentUser(Roles role);
}