package com.vti.rw41.servcie;

import com.vti.rw41.dto.AccountRequest;
import com.vti.rw41.entity.AccountEntity;
import com.vti.rw41.reposioty.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public AccountEntity registerAccount(AccountRequest request) {

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(request.getEmail());
        accountEntity.setBirthday(request.getBirthday());
        accountEntity.setFullName(request.getFullName());
        accountEntity.setPassword(request.getPassword());

        return accountRepository.saveAccount(accountEntity);
    }
}
