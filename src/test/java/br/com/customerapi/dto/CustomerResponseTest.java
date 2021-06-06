package br.com.customerapi.dto;

import br.com.customerapi.model.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerResponseTest {

    @Test
    @DisplayName("should convert customer model to customer response")
    void of() {
        final var result = CustomerResponse.of(customerModel());

        assertEquals("RENATO", result.getName());
        assertEquals(LocalDateTime.of(2021, 1, 1, 8, 0, 0), result.getRegisterAt());
        assertEquals(LocalDateTime.of(2021, 1, 5, 9, 30, 0), result.getUpdatedAt());
        assertEquals(25, result.getAge());
    }

    private Customer customerModel() {
        return Customer.builder()
                .birthDate(LocalDate.of(1996, 3, 4))
                .name("RENATO")
                .document("33322211177")
                .registerAt(LocalDateTime.of(2021, 1, 1, 8, 0, 0))
                .updatedAt(LocalDateTime.of(2021, 1, 5, 9, 30, 0))
                .build();
    }
}