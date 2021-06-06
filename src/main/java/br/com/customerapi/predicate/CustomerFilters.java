package br.com.customerapi.predicate;

import com.querydsl.core.BooleanBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerFilters {

    @Schema(description = "customer name", example = "RENATO ALEXSANDER", name = "name")
    private String name;

    @Schema(description = "customer document", example = "33322211199", name = "document")
    private String document;

    @Schema(description = "customer birth date", example = "1996-03-04", name = "birthDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public BooleanBuilder toPredicate() {
        return new CustomerPredicate()
                .withBirthDate(birthDate)
                .withDocument(document)
                .withName(name)
                .toBuild();
    }
}
