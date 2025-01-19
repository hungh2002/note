package com.ryu.note_backend.dto;

import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDTO {

    String id;

    String name;

    @Email(message = "Email should be valid")
    private String email;

    // @Size(min = 8, max = 16, message = "Password should be between 8 and 16
    // characters")
    private String password;
}
