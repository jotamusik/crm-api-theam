package com.agilemonkeys.crmapi.utils;

import com.agilemonkeys.crmapi.model.entity.Customer;
import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.model.request.CreateCustomerRequest;
import com.agilemonkeys.crmapi.model.request.CreateUserRequest;

public class Converter {
    public static User fromCreateUserRequest(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());
        return user;
    }

    public static Customer fromCreateCustomerRequest(CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer();
        customer.setName(createCustomerRequest.getName());
        customer.setSurname(createCustomerRequest.getSurname());
        customer.setPhotoUrl(createCustomerRequest.getPhotoUrl());
        return customer;
    }

}
