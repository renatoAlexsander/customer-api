package br.com.customerapi.service;

import br.com.customerapi.dto.CustomerRequest;
import br.com.customerapi.dto.CustomerResponse;
import br.com.customerapi.model.Customer;
import br.com.customerapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse save(CustomerRequest request) {
        final var customer = Customer.of(request);
        return Customer.of(customerRepository.save(customer));
    }

    public CustomerResponse update(CustomerRequest request) {
        var customer = customerRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found."));

        BeanUtils.copyProperties(request, customer, "id");
        return Customer.of(customerRepository.save(customer));
    }
}
