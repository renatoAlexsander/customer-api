package br.com.customerapi.service;

import br.com.customerapi.dto.CustomerRequest;
import br.com.customerapi.dto.CustomerResponse;
import br.com.customerapi.model.Customer;
import br.com.customerapi.predicate.CustomerFilters;
import br.com.customerapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse save(CustomerRequest request) {
        final var customer = Customer.of(request);
        return CustomerResponse.of(customerRepository.save(customer));
    }

    public CustomerResponse update(Long id, CustomerRequest request) {
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found."));

        BeanUtils.copyProperties(request, customer);
        return CustomerResponse.of(customerRepository.save(customer));
    }

    public Page<CustomerResponse> all(CustomerFilters filters, Pageable pageable) {
        final var predicate = filters.toPredicate();
        final var customers = customerRepository.findAll(predicate, pageable);

        return new PageImpl<>(customers.map(CustomerResponse::of).toList(), pageable, customers.getSize());
    }
}
