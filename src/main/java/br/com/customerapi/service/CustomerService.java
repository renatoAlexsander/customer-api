package br.com.customerapi.service;

import br.com.customerapi.dto.CustomerRequest;
import br.com.customerapi.dto.CustomerResponse;
import br.com.customerapi.model.Customer;
import br.com.customerapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse save(CustomerRequest request) {
        final var customer = Customer.of(request);
        return Customer.of(customerRepository.save(customer));
    }
}
