package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountsRepository;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountsRepository accountsRepository;

    public ResponseEntity<Account> getAccount (Long id){
        Account account1 = accountsRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("account does not exist"));

        return new ResponseEntity<>(account1, HttpStatus.OK);
    }


}
