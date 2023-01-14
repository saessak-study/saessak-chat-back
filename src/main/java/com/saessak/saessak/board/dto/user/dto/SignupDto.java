package com.saessak.saessak.board.dto.user.dto;

public record SignupDto (
        String name,
        String id,
        String pw,
        String mail
) {
}
