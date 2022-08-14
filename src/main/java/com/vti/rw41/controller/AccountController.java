package com.vti.rw41.controller;

import com.vti.rw41.dto.request.AccountRequest;
import com.vti.rw41.dto.request.LoginRequest;
import com.vti.rw41.entity.AccountEntity;
import com.vti.rw41.servcie.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public Page<AccountEntity> getAllAccounts(Pageable pageable) {

        return accountService.getAllAccounts(pageable);
    }

    @GetMapping("/principal")
    public UserDetails getCurrentAccount(@AuthenticationPrincipal UserDetails principal) {
        return principal;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        return accountService.login(request);
    }

    @PostMapping("/register")
    public AccountEntity registerAccount(@Valid @RequestBody
                                                 AccountRequest request) {

        return accountService.registerAccount(request);
    }

    @GetMapping("/{accountId}")
    public Optional<AccountEntity> getAccountById(@PathVariable Integer accountId) {

        return accountService.getAccountById(accountId);
    }

    @GetMapping("/like/{name}")
    public List<AccountEntity> getAccountById(@PathVariable String name) {

        return accountService.getAccountByName(name);
    }

}
