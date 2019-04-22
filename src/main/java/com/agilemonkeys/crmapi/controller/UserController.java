package com.agilemonkeys.crmapi.controller;


import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.enums.Roles;
import com.agilemonkeys.crmapi.model.request.UserRequest;
import com.agilemonkeys.crmapi.service.UserSecurityService;
import com.agilemonkeys.crmapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserSecurityService userSecurityService;

    @GetMapping("{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(
            @Valid
            @RequestBody
            UserRequest userRequest) {
        if ( userSecurityService.isCurrentUser(Roles.ROLE_ADMIN) )
            return userService.createUser(userRequest);
        else
            return null;
    }

}
