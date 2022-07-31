package com.vti.rw41.dto;


import com.vti.rw41.validation.EmailNotUnique;
import com.vti.rw41.validation.Password;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
public class AccountRequest {

    @Email
    @NotNull
    @EmailNotUnique
    private String email;

    @Password
    private String password;

    @Length(min = 5, max = 255)
    private String fullName;

    @PastOrPresent
    private LocalDate birthday;

}
