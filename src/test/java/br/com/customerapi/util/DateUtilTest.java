package br.com.customerapi.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class DateUtilTest {

    @Test
    @DisplayName("discovery age by date in years")
    void discoveryAgeTest() {
        final var birthDate = LocalDate.of(1996, 3, 4);
        final var result = DateUtil.discoveryAge(birthDate);
        final var expected = 25;

        Assertions.assertEquals(expected, result);
    }
}