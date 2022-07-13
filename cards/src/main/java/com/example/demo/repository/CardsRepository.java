package com.example.demo.repository;

import com.example.demo.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardsRepository extends JpaRepository<Cards, Long> {
    List<Cards> findByAccountId (int accountId);
}
