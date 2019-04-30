package com.agilemonkeys.crmapi.utils;

import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.enums.Roles;
import com.agilemonkeys.crmapi.repository.UserRepository;
import com.agilemonkeys.crmapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OnLoad {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        if ( !userService.existsUserWithUsername("admin") ) {
            User adminUser = new User("admin", bCryptPasswordEncoder.encode("admin"), new ArrayList<>());
            adminUser.getRoles().add(Roles.ROLE_ADMIN);
            adminUser.getRoles().add(Roles.ROLE_USER);
            userRepository.save(adminUser);
        }
    }
}
