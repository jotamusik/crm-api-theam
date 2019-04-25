package com.agilemonkeys.crmapi.controller;

import com.agilemonkeys.crmapi.model.entity.Customer;
import com.agilemonkeys.crmapi.model.request.CreateCustomerRequest;
import com.agilemonkeys.crmapi.model.request.UpdateCustomerRequest;
import com.agilemonkeys.crmapi.service.CustomerService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Page<Customer> getAllCustomers(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return customerService.getAllCustomers(pageable);
    }

    @GetMapping("{id}")
    public Customer getCustomerById(
            @PathVariable(name = "id") int id
    ) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer createCustomer(
            @Valid
            @RequestBody
            CreateCustomerRequest createCustomerRequest
    ) {
        return customerService.createCustomer(createCustomerRequest);
    }

    @PutMapping("{id}")
    public Customer updateCustomer(
            @PathVariable(name = "id") int id,
            @RequestBody UpdateCustomerRequest updateCustomerRequest
    ) {
        return customerService.updateCustomer(id, updateCustomerRequest);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(
            @PathVariable(name = "id") int id
    ) {
        customerService.deleteCustomer(id);
    }
}
