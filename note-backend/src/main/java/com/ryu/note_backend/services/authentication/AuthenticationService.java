package com.ryu.note_backend.services.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ryu.note_backend.dto.AccountDTO;
import com.ryu.note_backend.models.entities.Account;
import com.ryu.note_backend.models.repositories.AccountRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AuthenticationService {

    AccountRepository accountRepository;

    public AccountDTO register(AccountDTO accountDTO) {
        if (accountDTO.getEmail() == null || accountDTO.getPassword() == null) {
            throw new BadCredentialsException("Email and password are required");
        }

        if (accountRepository.existsByEmail(accountDTO.getEmail())) {
            throw new LockedException("Email already exists");
        }

        Account account = accountRepository
                .save(Account.builder()
                        .email(accountDTO.getEmail())
                        .name(accountDTO.getName())
                        .password(new BCryptPasswordEncoder(16).encode(accountDTO.getPassword()))
                        .build());

        return AccountDTO.builder().id(account.getId()).name(account.getName()).build();
    }

    public AccountDTO login(String email, String password) {
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            throw new UsernameNotFoundException("Account not found");
        }

        if (new BCryptPasswordEncoder(16).matches(password, account.getPassword())) {
            return AccountDTO.builder().id(account.getId()).name(account.getName()).build();
        } else {
            throw new BadCredentialsException("Invalid password");
        }
    }
}
