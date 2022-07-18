package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<Account> getAccountDetails (Customer customer);
}
