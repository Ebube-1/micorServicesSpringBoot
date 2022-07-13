package com.example.demo.registration;

import com.example.demo.model.Account;
import com.example.demo.registration.token.ConfirmationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody RegistrationRequest registrationRequest){
        return registrationService.registerAccount(registrationRequest);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {

        return registrationService.confirmToken(token);
    }

    @GetMapping("/resendToken")
    public String resend(@RequestParam("token") String oldToken){

        return registrationService.generateNewConfirmationToken(oldToken);

    }
}

