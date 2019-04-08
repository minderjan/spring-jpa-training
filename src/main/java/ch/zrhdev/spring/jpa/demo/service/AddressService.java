package ch.zrhdev.spring.jpa.demo.service;

import ch.zrhdev.spring.jpa.demo.entity.Address;
import ch.zrhdev.spring.jpa.demo.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jan Minder on 25.04.17.
 */

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    AddressRepository addressRepository;

    public Address saveOrUpdateAddress(Address address) {

        return addressRepository.save(address);

    }

    public Address getAddressById(Long addressId) {

        return addressRepository.findOne(addressId);

    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

}
