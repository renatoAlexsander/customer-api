package br.com.customerapi.predicate;

import com.querydsl.core.BooleanBuilder;

import java.time.LocalDate;
import java.util.Objects;

import static br.com.customerapi.model.QCustomer.customer;

public class CustomerPredicate {

    private final BooleanBuilder booleanBuilder = new BooleanBuilder();

    public CustomerPredicate withName(String name) {
        if (Objects.nonNull(name)) {
            booleanBuilder.and(customer.name.eq(name));
        }
        return this;
    }

    public CustomerPredicate withDocument(String document) {
        if (Objects.nonNull(document)) {
            booleanBuilder.and(customer.document.eq(document));
        }
        return this;
    }

    public CustomerPredicate withBirthDate(LocalDate birthDate) {
        if (Objects.nonNull(birthDate)) {
            booleanBuilder.and(customer.birthDate.eq(birthDate));
        }
        return this;
    }

    public BooleanBuilder toBuild() {
        return this.booleanBuilder;
    }
}
