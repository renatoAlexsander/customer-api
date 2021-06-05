package br.com.customerapi.util;

import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    private DateUtil() { }

    public static long discoveryAge(LocalDate birthDate) {
        if (ObjectUtils.isEmpty(birthDate)) {
            return 0;
        }
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
