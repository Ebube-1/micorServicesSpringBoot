package com.example.demo.controller;

import com.example.demo.config.CardsServiceConfig;
import com.example.demo.model.Account;
import com.example.demo.model.Cards;
import com.example.demo.model.Properties;
import com.example.demo.repository.CardsRepository;
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
public class CardsController {

    private final CardsRepository cardsRepository;
    private final CardsServiceConfig cardsConfig;

    @PostMapping("api/myCards")
    public List<Cards> getCardDetails(@RequestBody Account account) {
        List<Cards> cards = cardsRepository.findByAccountId(account.getAccountId());
        if (cards != null) {
            return cards;
        } else {
            return null;
        }

    }

    @GetMapping("/cards/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsConfig.getMsg(), cardsConfig.getBuildVersion(),
                cardsConfig.getMailDetails(), cardsConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
