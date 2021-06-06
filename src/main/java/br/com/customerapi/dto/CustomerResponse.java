package br.com.customerapi.dto;

import br.com.customerapi.model.Customer;
import br.com.customerapi.util.DateUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerResponse {

    @NotNull
    @Schema(description = "id generated in database", example = "1", name = "id")
    private Long id;

    @NotNull
    @NotEmpty
    @Schema(description = "customer name", example = "RENATO ALEXSANDER", name = "name")
    private String name;

    @NotNull
    @NotEmpty
    @Schema(description = "when register was saved", example = "2021-06-01T12:00:00", name = "registerAt")
    private LocalDateTime registerAt;

    @NotNull
    @NotEmpty
    @Schema(description = "last time register was saved", example = "2021-06-01T12:00:00", name = "updatedAt")
    private LocalDateTime updatedAt;

    @Schema(description = "customer age", example = "45", name = "age")
    private long age;

    public static CustomerResponse of(Customer customer) {
        var customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customer, customerResponse);

        customerResponse.setAge(DateUtil.discoveryAge(customer.getBirthDate()));

        return customerResponse;
    }
}
