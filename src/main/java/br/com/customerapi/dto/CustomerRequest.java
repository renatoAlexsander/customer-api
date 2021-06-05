package br.com.customerapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CustomerRequest {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotNull
    @NotEmpty
    private String document;

}
