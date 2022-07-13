package com.example.demo.registration;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.model.Account;
import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenRepository;
//import com.example.demo.registration.token.ConfirmationTokenService;
import com.example.demo.repository.AccountsRepository;
import com.example.demo.service.EmailSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class RegistrationService {

    private final AccountsRepository accountsRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailSender emailSender;


    public ResponseEntity<String> registerAccount(RegistrationRequest registrationRequest){
        if(!Objects.equals(registrationRequest.getPassword(), registrationRequest.getConfirmPassword())){
            throw new RuntimeException("Passwords do not match");
        }
        if(accountsRepository.findByEmail(registrationRequest.getEmail()) != null ){
            throw new UserAlreadyExistsException("User with " + registrationRequest.getEmail() + " already exists!");
        } else {
            Account account = new Account();
            account.setFirstName(registrationRequest.getFirstName());
            account.setLastName(registrationRequest.getLastName());
            account.setPhoneNumber(registrationRequest.getPhoneNumber());
            account.setEmail(registrationRequest.getEmail());
            account.setBvn(registrationRequest.getBvn());
            account.setPassword(registrationRequest.getPassword());
            account.setPin(registrationRequest.getPin());
            account.setCreateDt(LocalDate.now());
            accountsRepository.save(account);

            log.info("saved to db w/o token");
            //generate token
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    account
            );
        confirmationTokenRepository.save(confirmationToken);

        log.info("generated and saved token");

            //call email service and send token
            String link = "http://localhost:8080/api/users/confirm?token=" + token;
            log.info(link);
            emailSender.send(registrationRequest.getEmail(),
                    emailBody(registrationRequest.getFirstName(), link),
                    "Please verify your registration");

            log.info("sent mail");
            //verify token


            //update status of user

            //save in db

        }

        return new ResponseEntity<>("Registration successful, Kindly check mail for verification link.", HttpStatus.CREATED);
    }

    private String emailBody(String name, String link) {
        return "<div>\"<p> Dear "+ name +", </p>\"</div>" +
                "<p> Please click the link below to verify your registration, </p>" +
                "<h3><a href=\"" + link + "\" > VERIFY </a></h3>" +
               "<p>Thank you <br/> Fintech team </p>";
    }


    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken byToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token not found"));

        if(byToken.getConfirmedAt() != null){
            throw new RuntimeException("User already confirmed");
        }
        if(byToken.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new RuntimeException("token expired");
        }

        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
        Account account = accountsRepository.findById(byToken.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("account does not exist"));
        account.setStatus(true);

        return "confirmed";
    }

    public String generateNewConfirmationToken(String oldToken) {
        ConfirmationToken confirmationToken1 = confirmationTokenRepository.findByToken(oldToken)
                .orElseThrow(() -> new ResourceNotFoundException("no previous token"));

        String token = UUID.randomUUID().toString();
        confirmationToken1.setToken(token);
        confirmationToken1.setCreatedAt(LocalDateTime.now());
        confirmationToken1.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken1.setAccount(confirmationToken1.getAccount());

        confirmationTokenRepository.save(confirmationToken1);

        return "verify registration";
    }
}


