package com.saessak.saessak.board.dto.user.password_search;

import io.swagger.annotations.ApiModelProperty;

public record PasswordSearchRequestDto(
        @ApiModelProperty(value = "유저 이름", dataType = "string", required = true, example = "김새싹")
        String name,
        @ApiModelProperty(value = "유저 이메일", dataType = "string", required = true, example = "saessak@study.com")
        String email,
        @ApiModelProperty(value = "유저 아이디", dataType = "string", required = true, example = "saessak")
        String id
) {
    public PasswordSearchKeyInfo toEntity() {
        return PasswordSearchKeyInfo.builder().name(name).email(email).id(id).build();
    }
}