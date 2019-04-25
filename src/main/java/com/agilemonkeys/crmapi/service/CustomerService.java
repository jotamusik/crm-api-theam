package com.agilemonkeys.crmapi.service;

import com.agilemonkeys.crmapi.model.entity.Customer;
import com.agilemonkeys.crmapi.model.request.CreateCustomerRequest;
import com.agilemonkeys.crmapi.model.request.UpdateCustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Customer createCustomer(CreateCustomerRequest createCustomerRequest);
    Customer updateCustomer(int id, UpdateCustomerRequest updateCustomerRequest);
    Customer getCustomerById(int id);
    Page<Customer> getAllCustomers(Pageable pageable);
    void deleteCustomer(int id);
}
