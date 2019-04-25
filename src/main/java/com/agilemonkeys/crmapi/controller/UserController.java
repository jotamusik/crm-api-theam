package com.agilemonkeys.crmapi.controller;


import com.agilemonkeys.crmapi.exception.ExceptionResponseError;
import com.agilemonkeys.crmapi.exception.ForbiddenException;
import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.enums.Roles;
import com.agilemonkeys.crmapi.model.request.CreateUserRequest;
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
            CreateUserRequest createUserRequest) {

        if ( userSecurityService.isCurrentUser(Roles.ROLE_ADMIN) )
            return userService.createUser(createUserRequest);
        else
            throw new ForbiddenException(new ExceptionResponseError("user_role", "action_not_allowed"));
    }

    // ToDo: CRUD Users -> First define the users properties

}
