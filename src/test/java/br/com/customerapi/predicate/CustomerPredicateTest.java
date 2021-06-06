package br.com.customerapi.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerPredicateTest {

    private CustomerPredicate predicate;

    @BeforeEach
    void setUp() {
        predicate = new CustomerPredicate();
    }

    @Test
    @DisplayName("should return predicate by name")
    void predicateWithName() {
        final var result = predicate.withName("RENATO");

        assertEquals("customer.name = RENATO", predicate.toBuild().toString());
    }

    @Test
    @DisplayName("should return predicate by document")
    void predicateWithDocument() {
        final var result = predicate.withDocument("33322211177");

        assertEquals("customer.document = 33322211177", predicate.toBuild().toString());
    }

    @Test
    @DisplayName("should return predicate by birth date")
    void predicateWithBirthDate() {
        final var result = predicate.withBirthDate(LocalDate.of(1996, 3, 4));

        assertEquals("customer.birthDate = 1996-03-04", predicate.toBuild().toString());
    }
}