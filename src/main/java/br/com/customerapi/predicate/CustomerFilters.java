package br.com.customerapi.predicate;

import com.querydsl.core.BooleanBuilder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerFilters {

    private String name;
    private String document;
    private LocalDate birthDate;

    public BooleanBuilder toPredicate() {
        return new CustomerPredicate()
                .withBirthDate(birthDate)
                .withDocument(document)
                .withName(name)
                .toBuild();
    }
}
