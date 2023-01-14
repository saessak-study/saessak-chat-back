package com.saessak.saessak.board.dto.user.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginInfo {

    private String id;
    private String password;

    @Builder
    public LoginInfo(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
