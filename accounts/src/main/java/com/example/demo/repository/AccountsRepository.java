package com.example.demo.repository;

import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

    Account findByEmail (String email);
    boolean existsByEmail(String email);


    @Transactional
    @Modifying
    @Query("UPDATE Account a " +
            "SET a.status = TRUE WHERE a.email = ?1")
    int enableAccount(String email);
}
