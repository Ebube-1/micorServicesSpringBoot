package com.example.demo.controller;

import com.example.demo.config.LoansServiceConfig;
import com.example.demo.model.Account;
import com.example.demo.model.Loans;
import com.example.demo.model.Properties;
import com.example.demo.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoansRepository loansRepository;
    private final LoansServiceConfig loansConfig;

    @PostMapping("/api/myLoans")
    public List<Loans> getLoansDetails(@RequestBody Account account) {
        List<Loans> loans = loansRepository.findByAccountIdOrderByStartDtDesc(account.getAccountId());
        if (loans != null) {
            return loans;
        } else {
            return null;
        }

    }

    @GetMapping("/loans/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        com.example.demo.model.Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(),
                loansConfig.getMailDetails(), loansConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

}
