package br.com.customerapi.model;

import br.com.customerapi.dto.CustomerRequest;
import br.com.customerapi.dto.CustomerResponse;
import br.com.customerapi.util.DateUtil;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "register_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime registerAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String document;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    public static Customer of(CustomerRequest request) {
        var customer = new Customer();
        BeanUtils.copyProperties(request, customer);
        return customer;
    }

    public static CustomerResponse of(Customer customer) {
        var customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customer, customerResponse);

        customerResponse.setAge(DateUtil.discoveryAge(customer.getBirthDate()));

        return customerResponse;
    }
}
