package com.vti.rw41.servcie;

import com.vti.rw41.dto.request.AccountRequest;
import com.vti.rw41.dto.request.LoginRequest;
import com.vti.rw41.entity.AccountEntity;
import com.vti.rw41.entity.UserRole;
import com.vti.rw41.exeption.ApiException;
import com.vti.rw41.reposioty.AccountRepository;
import com.vti.rw41.reposioty.DepartmentRepository;
import com.vti.rw41.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Transactional
    public AccountEntity registerAccount(AccountRequest request) {

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(request.getEmail());
        accountEntity.setBirthday(request.getBirthday());
        accountEntity.setFullName(request.getFullName());
        String encode = passwordEncoder.encode(request.getPassword());
        accountEntity.setPassword(encode);
        accountEntity.setRole(UserRole.USER);
        accountRepository.save(accountEntity);
        return accountEntity;
    }

    public Optional<AccountEntity> getAccountById(Integer accountId) {
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
        if (accountEntity.isEmpty()) {
            throw new ApiException("account.not.exists");
        }
        return accountEntity;
    }

    public List<AccountEntity> getAccountByName(String name) {
        return accountRepository.timAccountTheoTen(name);
    }

    public Page<AccountEntity> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public ResponseEntity<String> login(LoginRequest request) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            if (passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
                String token = jwtTokenProvider.createToken(request.getEmail(), userDetails.getAuthorities());
                return ResponseEntity.ok(token);
            }
        } catch (UsernameNotFoundException ignored) {

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
