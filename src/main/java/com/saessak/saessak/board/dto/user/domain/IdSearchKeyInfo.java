package com.saessak.saessak.board.dto.user.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class IdSearchKeyInfo {

    String name;
    String email;

    @Builder
    public IdSearchKeyInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
