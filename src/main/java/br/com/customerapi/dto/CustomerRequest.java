package br.com.customerapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotNull
    @NotEmpty
    @Schema(description = "customer name", example = "RENATO ALEXSANDER", name = "name")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Schema(description = "customer birth date", example = "1996-03-04", name = "birthDate")
    private LocalDate birthDate;

    @NotNull
    @NotEmpty
    @Schema(description = "customer document only numbers", example = "33322211199", name = "document")
    private String document;

}
