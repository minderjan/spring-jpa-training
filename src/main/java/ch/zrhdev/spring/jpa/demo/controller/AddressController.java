package ch.zrhdev.spring.jpa.demo.controller;

import ch.zrhdev.spring.jpa.demo.entity.Address;
import ch.zrhdev.spring.jpa.demo.service.AddressService;
import io.swagger.annotations.Api;
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
@RequestMapping("/address")
@Api(value = "Address Service", tags = "Address")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    AddressService addressService;

    // ----------------- HTTP GET ---------------------------------------------

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
    public Address getAddressById(@PathVariable Long addressId) {
        return addressService.getAddressById(addressId);
    }


    // ----------------- HTTP POST ---------------------------------------------

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    ResponseEntity saveAddress(@RequestBody Address input) {
        try {
            if (addressService.saveOrUpdateAddress(input) != null) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ----------------- HTTP PUT ---------------------------------------------

    @RequestMapping(value = "/{addressId}", method = RequestMethod.POST)
    ResponseEntity updateAddress(@PathVariable Long addressId, @RequestBody Address input) {
        try {
            input.setAddressId(addressId);


            if (addressService.saveOrUpdateAddress(input) != null) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
