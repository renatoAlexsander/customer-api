package br.com.customerapi.controller;

import br.com.customerapi.dto.CustomerRequest;
import br.com.customerapi.dto.CustomerResponse;
import br.com.customerapi.service.CustomerService;
import br.com.customerapi.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/tests/customers_test.sql")
class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Test
    @DisplayName("status code 201 when save new customer")
    void save_created_whenBodyValid() throws Exception {
        final var request = request();

        final var requestBuilder = MockMvcRequestBuilders.post("/api/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(TestUtil.asJsonString(request));

        final var resultMatcher = status().isCreated();

        mockMvc.perform(requestBuilder)
            .andExpect(resultMatcher)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value("RENATO"))
            .andExpect(jsonPath("$.age").value(25));
    }

    @Test
    @DisplayName("status code 200 when update an existing customer")
    void update_ok_whenBodyValid() throws Exception {
        final var request = request();

        final var requestBuilder = MockMvcRequestBuilders.put("/api/customers/1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(TestUtil.asJsonString(request));

        final var resultMatcher = status().isOk();

        mockMvc.perform(requestBuilder)
            .andExpect(resultMatcher)
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("RENATO"))
            .andExpect(jsonPath("$.age").value(25));
    }

    private CustomerRequest request() {
        return CustomerRequest.builder()
            .birthDate(LocalDate.of(1996, 3, 4))
            .name("RENATO")
            .document("33322211177")
            .build();
    }
}