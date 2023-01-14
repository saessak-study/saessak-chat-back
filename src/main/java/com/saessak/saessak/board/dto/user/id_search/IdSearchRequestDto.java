package com.saessak.saessak.board.dto.user.id_search;

import com.saessak.saessak.board.dto.user.domain.IdSearchKeyInfo;
import io.swagger.annotations.ApiModelProperty;

public record IdSearchRequestDto(
        @ApiModelProperty(value = "유저 이름", dataType = "string", required = true, example = "김새싹")
        String name,
        @ApiModelProperty(value = "유저 이메일", dataType = "string", required = true, example = "saessak@study.com")
        String email
) {
    public IdSearchKeyInfo toEntity() {
        return IdSearchKeyInfo.builder().name(name).email(email).build();
    }
}