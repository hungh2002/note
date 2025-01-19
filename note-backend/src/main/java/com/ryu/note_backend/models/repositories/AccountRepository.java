package com.ryu.note_backend.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryu.note_backend.models.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
  Account findByEmail(String email);

  boolean existsByEmail(String email);
}
