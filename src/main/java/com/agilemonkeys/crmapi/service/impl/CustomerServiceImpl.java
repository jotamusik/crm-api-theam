package com.agilemonkeys.crmapi.service.impl;

import com.agilemonkeys.crmapi.exception.ExceptionResponseError;
import com.agilemonkeys.crmapi.exception.ResourceNotFoundException;
import com.agilemonkeys.crmapi.model.entity.Customer;
import com.agilemonkeys.crmapi.model.request.CreateCustomerRequest;
import com.agilemonkeys.crmapi.model.request.UpdateCustomerRequest;
import com.agilemonkeys.crmapi.repository.CustomerRepository;
import com.agilemonkeys.crmapi.service.CustomerService;
import com.agilemonkeys.crmapi.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.agilemonkeys.crmapi.utils.Converter.fromCreateCustomerRequest;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserSecurityService userSecurityService;

    @Override
    public Customer createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer newCustomer = fromCreateCustomerRequest(createCustomerRequest);
        newCustomer.setCreator(userSecurityService.getCurrentUser());
        newCustomer.setLastModifier(userSecurityService.getCurrentUser());
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(int id, UpdateCustomerRequest updateCustomerRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if ( optionalCustomer.isEmpty() )
            throw new ResourceNotFoundException(new ExceptionResponseError("customer", "not_exists"));

        Customer customer = optionalCustomer.get();
        updateCustomerInfo(updateCustomerRequest, customer);

        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if ( optionalCustomer.isPresent() )
            return optionalCustomer.get();
        else
            throw new ResourceNotFoundException(new ExceptionResponseError("customer", "not_exists"));
    }

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public void deleteCustomer(int id) {
        if ( customerNotExists(id) )
            throw new ResourceNotFoundException(new ExceptionResponseError("customer", "not_exists"));
        customerRepository.deleteById(id);
    }

    private boolean customerExists(int id) {
        return customerRepository.existsById(id);
    }

    private boolean customerNotExists(int id) {
        return !customerExists(id);
    }

    private void updateCustomerInfo(UpdateCustomerRequest updateCustomerRequest, Customer customer) {
        if ( updateCustomerRequest.getName() != null )
            if ( !updateCustomerRequest.getName().isEmpty() )
                customer.setName(updateCustomerRequest.getName());

        if ( updateCustomerRequest.getSurname() != null )
            if ( !updateCustomerRequest.getSurname().isEmpty() )
                customer.setSurname(updateCustomerRequest.getSurname());

        if ( updateCustomerRequest.getPhotoUrl() != null )
            if ( !updateCustomerRequest.getPhotoUrl().isEmpty() )
                customer.setPhotoUrl(updateCustomerRequest.getPhotoUrl());

        customer.setLastModifier(userSecurityService.getCurrentUser());
    }
}
