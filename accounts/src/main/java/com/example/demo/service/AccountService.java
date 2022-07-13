package com.example.demo.service;

import com.example.demo.model.Account;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<Account> getAccount (Long id);
}
