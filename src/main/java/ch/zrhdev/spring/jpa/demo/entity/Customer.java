package ch.zrhdev.spring.jpa.demo.entity;



import ch.zrhdev.spring.jpa.demo.json.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jan Minder on 25.04.17.
 */

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Compact.class)
    private Long id;

    @JsonView(View.Compact.class)
    private String name;

    @JsonView(View.Compact.class)
    @OneToMany(targetEntity=Address.class, mappedBy="customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> _addresses;

    public Customer(String name) {
        this.name = name;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return _addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this._addresses = addresses;
    }

    @Override
    public String toString() {

        String returnString = "";

        returnString += "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", _addresses=" ;

                for(Address adr : _addresses) {
                    returnString += System.lineSeparator();
                    returnString += adr.toString();
                }

        returnString += '}';

                return returnString;
    }
}
