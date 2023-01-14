package com.saessak.saessak.board.dto.user.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PasswordSearchKeyInfo {

    private String name;
    private String email;
    private String id;

    @Builder
    public PasswordSearchKeyInfo(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }
}
