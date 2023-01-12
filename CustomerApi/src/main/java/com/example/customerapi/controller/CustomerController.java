package com.example.customerapi.controller;

import com.example.customerapi.entity.Customer;
import com.example.customerapi.model.response.ServerResponse;
import com.example.customerapi.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/customer")
@RestController
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping("")
    @CrossOrigin
    private ResponseEntity<List<Customer>> getAllCustomer() {

        return ResponseEntity.ok()
                .body(customerRepository.findAll());
    }

    @GetMapping("/{customerId}")
    private ResponseEntity<Customer> getCustomer(@PathVariable int customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            return ResponseEntity.ok()
                    .body(customer.get());
        }

        return ResponseEntity.notFound().build();


    }

    @CrossOrigin
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    private ResponseEntity<HttpStatus> createCustomer(@Validated @RequestBody Customer createUserRequest) {

        Customer customer = new Customer();

        BeanUtils.copyProperties(createUserRequest, customer);

        customerRepository.save(customer);

        return ResponseEntity.ok()
                .body(HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/{customerId}")
    private ResponseEntity<ServerResponse> updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) {

        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
        ServerResponse response = new ServerResponse();

        if (existingCustomer.isPresent()) {
            BeanUtils.copyProperties(existingCustomer, customer);
            customerRepository.save(existingCustomer.get());

            response.setHttpStatus(HttpStatus.OK);
            response.setErrorMessage("");

            return ResponseEntity.ok().body(response);
        }
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setErrorMessage("No Customer was found with given id: " + customerId);

        return ResponseEntity.ok().body(response);
    }

    @CrossOrigin
    @DeleteMapping("/{customerId}")
    private ResponseEntity<ServerResponse> deleteCustomer(@PathVariable int customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);
        ServerResponse response = new ServerResponse();

        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            response.setHttpStatus(HttpStatus.OK);
            response.setErrorMessage("Customer successful deleted!");

            return ResponseEntity.ok().body(response);
        }

        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setErrorMessage("No Customer was found with given id: " + customerId);

        return ResponseEntity.ok().body(response);
    }
}
