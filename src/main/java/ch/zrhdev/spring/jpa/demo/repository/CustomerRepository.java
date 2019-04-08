package ch.zrhdev.spring.jpa.demo.repository;

import ch.zrhdev.spring.jpa.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jan Minder on 25.04.17.
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
