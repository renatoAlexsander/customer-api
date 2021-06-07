package br.com.customerapi.controller;

import br.com.customerapi.dto.CustomerRequest;
import br.com.customerapi.dto.CustomerResponse;
import br.com.customerapi.predicate.CustomerFilters;
import br.com.customerapi.service.CustomerService;
import br.com.customerapi.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    @DisplayName("status code 400 when send body invalid")
    void save_badRequest_whenNotSendBody() throws Exception {
        final var requestBuilder = MockMvcRequestBuilders.post("/api/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);

        final var resultMatcher = status().isBadRequest();

        mockMvc.perform(requestBuilder).andExpect(resultMatcher);
    }

    @Test
    @DisplayName("status code 415 when not send content type json")
    void save_unsupportedMediaType_whenSendContentTypeTextPlain() throws Exception {
        final var requestBuilder = MockMvcRequestBuilders.post("/api/customers")
            .contentType(MediaType.TEXT_PLAIN)
            .content("Some text");

        final var resultMatcher = status().isUnsupportedMediaType();

        mockMvc.perform(requestBuilder).andExpect(resultMatcher);
    }

    @Test
    @DisplayName("status code 201 when save new customer")
    void save_created_whenBodyValid() throws Exception {
        final var request = request();
        when(customerService.save(request)).thenReturn(response());

        final var requestBuilder = MockMvcRequestBuilders.post("/api/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(TestUtil.asJsonString(request));

        final var resultMatcher = status().isCreated();

        mockMvc.perform(requestBuilder)
            .andExpect(resultMatcher)
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("RENATO"))
            .andExpect(jsonPath("$.age").value(25));
    }

    @Test
    @DisplayName("status code 200 when update an existing customer")
    void update_ok_whenBodyValid() throws Exception {
        final var request = request();
        when(customerService.update(1L, request)).thenReturn(response());

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

    @Test
    @DisplayName("status code 200 and list pageable customers")
    void all_ok_whenFindAllCustomersSavedPageable() throws Exception {
        final Page<CustomerResponse> response = new PageImpl<>(List.of(response()));

        when(customerService.all(any(CustomerFilters.class), any(Pageable.class))).thenReturn(response);

        final var requestBuilder = MockMvcRequestBuilders.get("/api/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);

        final var resultMatcher = status().isOk();

        mockMvc.perform(requestBuilder)
            .andExpect(resultMatcher)
            .andExpect(jsonPath("$.totalPages").value(1))
            .andExpect(jsonPath("$.totalElements").value(1))
            .andExpect(jsonPath("$.content.[0].id").value(1))
            .andExpect(jsonPath("$.content.[0].name").value("RENATO"))
            .andExpect(jsonPath("$.content.[0].age").value(25));
    }

    private CustomerRequest request() {
        return CustomerRequest.builder()
            .birthDate(LocalDate.of(1996, 3, 4))
            .name("RENATO")
            .document("33322211177")
            .build();
    }

    private CustomerResponse response() {
        return CustomerResponse.builder()
            .id(1L)
            .name("RENATO")
            .age(25)
            .build();
    }
}