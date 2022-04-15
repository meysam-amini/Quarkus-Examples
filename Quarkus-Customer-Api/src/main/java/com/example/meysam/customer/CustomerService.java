package com.example.meysam.customer;

import com.example.meysam.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final Logger logger;

    public List<Customer> findAll(){
        return customerRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    public Optional<Customer> findById(Integer customerId) {
        return customerRepository.findByIdOptional(customerId);
    }

    @Transactional
    public Customer save(Customer customer) {
        Customer entity = customer;
        customerRepository.persist(entity);
        return entity;
    }

    @Transactional
    public Customer update(Customer customer) {
        if (customer.getCustomerId() == null) {
            throw new ServiceException("Customer does not have a customerId");
        }
        Optional<Customer> optional = customerRepository.findByIdOptional(customer.getCustomerId());
        if (optional.isEmpty()) {
            throw new ServiceException(String.format("No Customer found for customerId[%s]", customer.getCustomerId()));
        }
        Customer entity = optional.get();
        entity.setFirstName(customer.getFirstName());
        entity.setMiddleName(customer.getMiddleName());
        entity.setLastName(customer.getLastName());
        entity.setSuffix(customer.getSuffix());
        entity.setEmail(customer.getEmail());
        entity.setPhone(customer.getPhone());
        customerRepository.persist(entity);
        return entity;

    }
}
