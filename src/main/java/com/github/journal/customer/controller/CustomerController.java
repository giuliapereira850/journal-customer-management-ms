package com.github.journal.customer.controller;

import com.github.journal.customer.api.CustomerApi;
import com.github.journal.customer.model.Customer;
import com.github.journal.customer.model.CustomerFVO;
import com.github.journal.customer.model.CustomerMVO;
import com.github.journal.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        // TODO: Implement function
        return null;
    }

    @Override
    public ResponseEntity<Customer> createCustomer(CustomerFVO customerFVO, String fields) {
        // TODO: Implement function
        return CustomerApi.super.createCustomer(customerFVO, fields);
    }

    @Override
    public ResponseEntity<List<Customer>> listCustomer(String fields, Integer offset, Integer limit) {
        // TODO: Implement function
        return CustomerApi.super.listCustomer(fields, offset, limit);
    }

    @Override
    public ResponseEntity<Customer> retrieveCustomer(String id, String fields) {
        // TODO: Implement function
        return CustomerApi.super.retrieveCustomer(id, fields);
    }

    @Override
    public ResponseEntity<Customer> patchCustomer(String id, CustomerMVO customerMVO, String fields) {
        // TODO: Implement function
        return CustomerApi.super.patchCustomer(id, customerMVO, fields);
    }

    @PutMapping("/customer/{username}")
    public ResponseEntity<Customer> updateUsername(@PathVariable String username, @Valid @RequestBody CustomerMVO customerMVO) {
        // TODO: Implement function
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(String id) {
        // TODO: Implement function
        return CustomerApi.super.deleteCustomer(id);
    }

}
