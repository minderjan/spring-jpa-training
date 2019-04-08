package ch.zrhdev.spring.jpa.demo.entity;



import ch.zrhdev.spring.jpa.demo.json.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

/**
 * Created by Jan Minder on 25.04.17.
 */

@Entity
public class Address {

    @Id
    @JsonView(View.Compact.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @JsonView(View.Compact.class)
    private String street;

    @JsonView(View.Compact.class)
    private String houseNumber;

    @JsonView(View.Compact.class)
    private Long plz;

    @JsonView(View.Compact.class)
    private String city;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    public Address(String street, String houseNumber, long plz, String city) {
        this.addressId = addressId;
        this.street = street;
        this.houseNumber = houseNumber;
        this.plz = plz;
        this.city = city;
    }

    public Address() {
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Long getPlz() {
        return plz;
    }

    public void setPlz(Long plz) {
        this.plz = plz;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", plz=" + plz + '\'' +
                ", customer_id="+ customer.getId() +
                ", city='" + city + '\'' +
                '}';
    }
}
