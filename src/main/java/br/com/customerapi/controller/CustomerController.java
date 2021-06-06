package br.com.customerapi.controller;

import br.com.customerapi.dto.CustomerRequest;
import br.com.customerapi.dto.CustomerResponse;
import br.com.customerapi.predicate.CustomerFilters;
import br.com.customerapi.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "CustomerController", description = "defines customers resources.")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "save new customer")
    @ApiResponse(responseCode = "201", description = "customer save")
    @ApiResponse(responseCode = "500", description = "customer don't save")
    public CustomerResponse save(@Valid @RequestBody CustomerRequest request) {
        return customerService.save(request);
    }

    @PutMapping
    @Operation(summary = "update existing customer")
    @ApiResponse(responseCode = "201", description = "customer update")
    @ApiResponse(responseCode = "400", description = "body without id.")
    @ApiResponse(responseCode = "404", description = "customer not found by id.")
    public CustomerResponse update(@Valid @RequestBody CustomerRequest request) {
        if (Objects.isNull(request.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "body must send id.");
        }
        return customerService.update(request);
    }

    @GetMapping
    @Operation(summary = "get customers pageable")
    @ApiResponse(responseCode = "201", description = "customer save")
    @ApiResponse(responseCode = "500", description = "customer don't save")
    public Page<CustomerResponse> all(CustomerFilters filters, Pageable pageable) {
        return customerService.all(filters, pageable);
    }
}
