package br.com.customerapi.service;

import br.com.customerapi.dto.CustomerRequest;
import br.com.customerapi.model.Customer;
import br.com.customerapi.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository repository;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(repository);
    }

    @Test
    @DisplayName("should save a new customer")
    void save() {
        final var customer = customerModel();

        when(repository.save(any(Customer.class)))
                .thenReturn(customer);

        final var result = customerService.save(request());

        assertEquals(1L, result.getId());
        assertEquals("RENATO", result.getName());
        assertEquals(25, result.getAge());
    }

    @Test
    @DisplayName("should throw not found when try update customer not exist.")
    void shouldThrowExpcetionWhenTryUpdateCustomerNotExist() {
        final var customer = customerModel();

        final var assertThrows = assertThrows(ResponseStatusException.class,
                () -> customerService.update(1L, request()));

        assertEquals(HttpStatus.NOT_FOUND, assertThrows.getStatus());
        assertEquals("Customer not found.", assertThrows.getReason());
    }

    @Test
    @DisplayName("should update an existing customer")
    void update() {
        var customer = customerModel();

        when(repository.findById(1L)).thenReturn(Optional.of(customer));

        final var customerEdited = Customer.builder()
                .id(1L)
                .registerAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .birthDate(LocalDate.of(1995, 3, 4))
                .name("ALBERTO")
                .document("33322211177")
                .build();

        when(repository.save(customer)).thenReturn(customerEdited);

        var request = request();
        request.setName("ALBERTO");

        final var result = customerService.update(1L, request);

        assertEquals(1L, result.getId());
        assertEquals("ALBERTO", result.getName());
        assertEquals(26, result.getAge());
    }

    private CustomerRequest request() {
        return CustomerRequest.builder()
                .birthDate(LocalDate.of(1996, 3, 4))
                .name("RENATO")
                .document("33322211177")
                .build();
    }

    private Customer customerModel() {
        return Customer.builder()
                .id(1L)
                .registerAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .birthDate(LocalDate.of(1996, 3, 4))
                .name("RENATO")
                .document("33322211177")
                .build();
    }
}