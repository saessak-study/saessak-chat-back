package com.saessak.saessak.board.dto.user.password_search;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordSearchResponseDto {

    private String password;

    @Builder
    public PasswordSearchResponseDto(String password) {
        this.password = password;
    }
}
