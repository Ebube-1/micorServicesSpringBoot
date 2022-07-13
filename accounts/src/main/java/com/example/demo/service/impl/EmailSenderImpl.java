package com.example.demo.service.impl;

import com.example.demo.service.EmailSender;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailSenderImpl.class);
    private final JavaMailSender mailSender;

    @Override
    @Async 
    public void send(String to, String email, String subject) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("chinekeebube@gmail.com");
            mailSender.send(mimeMessage);
            LOGGER.info("mail sent successfully");
        }catch(MessagingException e){
            LOGGER.error("failed to send messsage",e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
