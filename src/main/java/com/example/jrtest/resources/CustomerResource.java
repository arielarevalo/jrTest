package com.example.jrtest.resources;

import com.example.jrtest.Customer;
import com.example.jrtest.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerResource {

  // ASK ABOUT https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
  // 4.2.3
  @Autowired private CustomerService repository;

  @PostMapping
  public ResponseEntity<String> createCustomer(@RequestBody Customer pCustomer) {
    String response = repository.save(pCustomer).toString();
    return new ResponseEntity<>("Created: " + response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<String> getAllCustomers() {
    String response = repository.findAllByOrderByAgeDescSalaryDesc().toString();
    return ResponseEntity.ok(response);
  }

  /* CRUDRepository.save() supports create and update */
  @PutMapping(value = "/{id}")
  public ResponseEntity<String> updateCustomer(
      @RequestBody Customer pCustomer, @PathVariable("id") Long id) {
    String response = repository.save(pCustomer).toString();
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long pId) {
    repository.deleteById(pId);
    String response = "Deleted customer " + pId;
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/{id}/age")
  public ResponseEntity<String> getCustomerAge(@PathVariable("id") Long pId) {
    Optional<Customer> c = repository.findById(pId); // ASK ABOUT FINDAGEBYID
    String response = c.isPresent() ? Integer.toString(c.get().age) : "CUSTOMER NOT FOUND";
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/salarycompare/{salary}")
  public ResponseEntity<String> getHigherLowerSalaryCount(@PathVariable("salary") Integer pSalary) {
    String response =
        pSalary != null
            ? "Higher salaries: "
                + repository.countBySalaryGreaterThan(pSalary)
                + "\tLower salaries: "
                + repository.countBySalaryLessThan(pSalary)
            : "SALARY IS NULL";
    return ResponseEntity.ok(response);
  }
}
