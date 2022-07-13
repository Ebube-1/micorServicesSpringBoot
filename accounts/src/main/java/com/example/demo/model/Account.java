package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_acc_id")
    private Long id;

    @NotEmpty
    @Size(min = 3, message = "First name can not be less than 3")
    private String firstName;

    @NotEmpty
    @Size(min = 3, message = "Last name can not be less than 3")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "mobile_number")
    @NotEmpty
    @Size(min = 10, message = "Phone number should have at least 10 characters")
    private String phoneNumber;

    @NotEmpty
    @Size(min = 11, max = 11, message = "BVN number must not be less than 11")
    private String bvn;

    @Size(min = 4, message = "Pin can not be more than 4")
    private String pin;


    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;

    private boolean status;

    @Column(name = "create_dt")
    private LocalDate createDt;

}
