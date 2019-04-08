package ch.zrhdev.spring.jpa.demo.repository;

import ch.zrhdev.spring.jpa.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by
 * Jan Minder on 25.04.17.
 */

public interface AddressRepository extends JpaRepository<Address, Long> {

    public List<Address> findAdressByCustomerId(Long customerId);

}
