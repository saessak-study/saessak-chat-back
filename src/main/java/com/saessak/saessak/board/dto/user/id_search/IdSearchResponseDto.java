package com.saessak.saessak.board.dto.user.id_search;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IdSearchResponseDto {
    @ApiModelProperty(value = "요청한 데이터로 찾은 아이디", dataType = "string", required = true, example = "saessak")
    private String id;

    @Builder
    public IdSearchResponseDto(String id) {
        this.id = id;
    }

}
