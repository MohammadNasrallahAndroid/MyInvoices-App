package com.mohammadnasrallah.maliaassignment.domain.repository;

import com.mohammadnasrallah.maliaassignment.domain.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getCustomerList();
}
