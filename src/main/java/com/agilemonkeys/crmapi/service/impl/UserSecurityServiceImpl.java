package com.agilemonkeys.crmapi.service.impl;

import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.enums.Roles;
import com.agilemonkeys.crmapi.repository.UserRepository;
import com.agilemonkeys.crmapi.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    @Autowired
    UserRepository userRepository;

    public User getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")
                ? null
                : (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean isCurrentUser(Roles role) {
        User currentUser = getCurrentUser();
        if ( currentUser == null )
            return false;
        return currentUser.getRoles().stream().anyMatch(currentRole -> currentRole == role);
    }
}
