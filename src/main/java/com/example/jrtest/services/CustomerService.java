package com.example.jrtest.services;

import com.example.jrtest.Customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerService extends CrudRepository<Customer, Long> {
  List<Customer> findAllByOrderByAgeDescSalaryDesc();

  Integer countBySalaryGreaterThan(Integer salary);

  Integer countBySalaryLessThan(Integer salary);
}
