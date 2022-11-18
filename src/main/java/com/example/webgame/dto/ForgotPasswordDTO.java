package com.example.webgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordDTO {
    @NotEmpty(message = "Password can not be empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[A-Z])(?=.*[-\\#\\$\\.\\%\\&\\*\\@])(?=.*[a-zA-Z]).{8,16}$",
            message = "Password: 8 -> 16 characters, contains at least 1 uppercase, contains at least 1 lowercase, " +
                    "contains at least 1 special characters")
    private String password;

    private String token;
}
