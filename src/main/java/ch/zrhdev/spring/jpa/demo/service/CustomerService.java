package ch.zrhdev.spring.jpa.demo.service;

import ch.zrhdev.spring.jpa.demo.entity.Customer;
import ch.zrhdev.spring.jpa.demo.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jan Minder on 25.04.17.
 */

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerRepository customerRepository;

    public Customer saveOrUpdateCustomer(Customer customer) {

        return customerRepository.save(customer);

    }

    public Customer getCustomerById(Long customerId) {

        return customerRepository.findOne(customerId);

    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
