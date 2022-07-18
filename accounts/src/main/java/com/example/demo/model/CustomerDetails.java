package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CustomerDetails {

    private Account accounts;
    private List<Loans> loans;
    private List<Cards> cards;
}
