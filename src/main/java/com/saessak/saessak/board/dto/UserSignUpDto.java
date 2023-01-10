package com.saessak.saessak.board.dto;

import io.swagger.annotations.ApiModelProperty;


public record UserSignUpDto(
        @ApiModelProperty(value = "이름", dataType = "string", required = true,example = "새싹 스터디")
        String name,
        @ApiModelProperty(value = "아이디", dataType = "string", required = true, example = "saessak")
        String id,
        @ApiModelProperty(value = "비밀번호", dataType = "string", required = true, example = "password")
        String pw,
        @ApiModelProperty(value = "이메일", dataType = "string", required = true, example = "saessak@study.com")
        String mail
) {
    public User toEntity() {
        return User.builder().name(name).id(id).pw(pw).mail(mail).build();
    }
}
