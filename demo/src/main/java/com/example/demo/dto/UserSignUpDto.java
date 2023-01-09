package com.example.demo.dto;

import lombok.Data;

public record UserSignUpDto (
    String name,
    String id,
    String pw,
    String mail
) {
    public User toEntity() {
        return User.builder().name(name).id(id).pw(pw).mail(mail).build();
    }
}
