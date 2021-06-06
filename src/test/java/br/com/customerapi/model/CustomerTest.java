package br.com.customerapi.model;

import br.com.customerapi.dto.CustomerRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    @Test
    @DisplayName("should convert customer request to customer model")
    void of() {
        final var result = Customer.of(request());

        assertEquals("RENATO", result.getName());
        assertEquals("33322211177", result.getDocument());
        assertEquals(LocalDate.of(1996, 3, 4), result.getBirthDate());
    }

    private CustomerRequest request() {
        return CustomerRequest.builder()
                .birthDate(LocalDate.of(1996, 3, 4))
                .name("RENATO")
                .document("33322211177")
                .build();
    }
}