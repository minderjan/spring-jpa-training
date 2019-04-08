package ch.zrhdev.spring.jpa.demo.controller;

import ch.zrhdev.spring.jpa.demo.entity.Address;
import ch.zrhdev.spring.jpa.demo.entity.Customer;
import ch.zrhdev.spring.jpa.demo.json.View;
import ch.zrhdev.spring.jpa.demo.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jan Minder on 25.04.17.
 */

@RestController
@RequestMapping("/customer")
@Api(value = "Customer Service", tags = "Customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    // ----------------- HTTP GET ---------------------------------------------
    @ApiOperation(value = "Get all Customers from database",
            notes = "Provides all Customer Objects including the address as an array")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @JsonView(View.Compact.class)
    public List<Customer> getAllCustomers(@RequestParam(value="adr", required=false, defaultValue = "true") Boolean withAddress) {

        if (withAddress) {
            return customerService.getAllCustomers();
        }
        else
        {
            List<Customer> customers = customerService.getAllCustomers();
            // Verlinke die Adresse mit dem Kunden
            for (Customer cust: customers)
                cust.setAddresses(null);

            return customers;
        }

    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    @JsonView(View.Compact.class)
    public Customer getCustomerById(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId);
    }


    // ----------------- HTTP POST ---------------------------------------------

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    ResponseEntity saveCustomer(@RequestBody Customer input) {

        try {

            // Verlinke die Adresse mit dem Kunden
            for (Address adr: input.getAddresses())
                adr.setCustomer(input);

            if (customerService.saveOrUpdateCustomer(input) != null) {
                return new ResponseEntity(HttpStatus.CREATED);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ----------------- HTTP PUT ---------------------------------------------

    @RequestMapping(value = "/{customerId}", method = RequestMethod.PUT)
    ResponseEntity updateCustomer(@PathVariable Long customerId, @RequestBody Customer input) {

        try {
            // Verlinke den Customer mit einem Objekt
            input.setId(customerId);

            // Verlinke die Adresse mit dem Kunden
            for (Address adr: input.getAddresses())
                adr.setCustomer(input);

            if (customerService.saveOrUpdateCustomer(input) != null) {
                return new ResponseEntity(HttpStatus.CREATED);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
