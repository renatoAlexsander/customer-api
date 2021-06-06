package br.com.customerapi.dto;

import br.com.customerapi.model.Customer;
import br.com.customerapi.util.DateUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    @NotNull
    @Schema(description = "id generated in database", example = "1", name = "id")
    private Long id;

    @NotNull
    @NotEmpty
    @Schema(description = "customer name", example = "RENATO ALEXSANDER", name = "name")
    private String name;

    @Schema(description = "customer age", example = "45", name = "age")
    private long age;

    public static CustomerResponse of(Customer customer) {
        var customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customer, customerResponse);

        customerResponse.setAge(DateUtil.discoveryAge(customer.getBirthDate()));

        return customerResponse;
    }
}
