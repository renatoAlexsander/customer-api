package br.com.customerapi.repository;

import br.com.customerapi.model.Customer;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
        extends PagingAndSortingRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {

}
