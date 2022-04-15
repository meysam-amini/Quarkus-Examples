package com.example.meysam.customer;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Data
@ToString
@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "suffix")
    private String suffix;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "phone")
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return customerId.equals(customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
