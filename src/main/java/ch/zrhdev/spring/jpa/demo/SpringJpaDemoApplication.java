package ch.zrhdev.spring.jpa.demo;

import ch.zrhdev.spring.jpa.demo.entity.Address;
import ch.zrhdev.spring.jpa.demo.entity.Customer;
import ch.zrhdev.spring.jpa.demo.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringJpaDemoApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringJpaDemoApplication.class);

	@Autowired
    CustomerService customerService;


	public static void main(String[] args) {
		SpringApplication.run(SpringJpaDemoApplication.class, args);
	}


	@Override
	@Transactional
	public void run(String... strings) throws Exception {

		logger.info("Biespiel-Daten in der DB speichern");

		// Erstelle eine Liste mit Adressen
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(new Address("Hardturmstrasse", "3", 8005, "Zürich"));
		addresses.add(new Address("Ueberlandstrasse", "5", 8050, "Zürich"));

		// Erstelle einen Kunden
		Customer customer = new Customer("Jan Minder");
		customer.setAddresses(addresses);

		// Verlinke die Adresse mit dem Kunden
		for (Address adr: customer.getAddresses())
			adr.setCustomer(customer);

		// Speichern des Kunden in der Datenbank
		customerService.saveOrUpdateCustomer(customer);

		// fetch all books
		for (Customer cust : customerService.getAllCustomers()) {
			logger.info(cust.toString());
		}


	}



}
