package br.com.customerapi.predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerFiltersTest {

    @Test
    @DisplayName("should map all filters to predicate")
    void filtersPredicate() {
        CustomerFilters filters = new CustomerFilters();
        filters.setName("RENATO");
        filters.setBirthDate(LocalDate.of(1996, 3, 4));
        filters.setDocument("33322211177");

        assertEquals("customer.birthDate = 1996-03-04 " +
                "&& customer.document = 33322211177 " +
                "&& customer.name = RENATO", filters.toPredicate().toString());
    }
}