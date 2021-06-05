package br.com.customerapi.controller;

import br.com.customerapi.dto.CustomerRequest;
import br.com.customerapi.dto.CustomerResponse;
import br.com.customerapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse save(@Valid @RequestBody CustomerRequest request) {
        return customerService.save(request);
    }

    @PutMapping
    public CustomerResponse update(@Valid @RequestBody CustomerRequest request) {
        if (Objects.isNull(request.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "body must send id.");
        }
        return customerService.update(request);
    }
}
