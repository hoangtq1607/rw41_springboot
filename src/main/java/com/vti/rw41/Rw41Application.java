package com.vti.rw41;

import com.vti.rw41.reposioty.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class Rw41Application {

    public static void main(String[] args) {
        SpringApplication.run(Rw41Application.class, args);
    }

    @Autowired
    AccountRepository accountRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            accountRepository.updateNameById("Nguyen Van B", 1);
        };
    }

}
