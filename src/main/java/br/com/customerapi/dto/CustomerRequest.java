package br.com.customerapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CustomerRequest {

    private Long id;

    @NotNull
    @NotEmpty
    @Schema(description = "customer name", example = "RENATO ALEXSANDER", name = "name")
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "customer birth date", example = "04/03/1996", name = "birthDate")
    private LocalDate birthDate;

    @NotNull
    @NotEmpty
    @Schema(description = "customer document only numbers", example = "33322211199", name = "document")
    private String document;

}
