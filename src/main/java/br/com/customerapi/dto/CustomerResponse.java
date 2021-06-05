package br.com.customerapi.dto;

import br.com.customerapi.model.Customer;
import br.com.customerapi.util.DateUtil;
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
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private LocalDateTime registerAt;

    @NotNull
    @NotEmpty
    private LocalDateTime updatedAt;

    private long age;

    public static CustomerResponse of(Customer customer) {
        var customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customer, customerResponse);

        customerResponse.setAge(DateUtil.discoveryAge(customer.getBirthDate()));

        return customerResponse;
    }
}
