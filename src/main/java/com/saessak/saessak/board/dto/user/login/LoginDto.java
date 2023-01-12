package com.saessak.saessak.board.dto.user.login;

import io.swagger.annotations.ApiModelProperty;

public record LoginDto(
        @ApiModelProperty(value = "아이디", dataType = "string", required = true, example = "saessak")
        String id,
        @ApiModelProperty(value = "비밀번호", dataType = "string", required = true, example = "study")
        String password
) {
    public LoginInfo toEntity() {
        return LoginInfo.builder().id(id).password(password).build();
    }
}
