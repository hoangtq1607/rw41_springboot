package com.vti.rw41.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String phoneNumber;

    private String password;

//    @Column(name = "fullName")
    private String fullName;

    private LocalDate birthday;


    @Enumerated(EnumType.STRING)
    private UserRole role;

}
