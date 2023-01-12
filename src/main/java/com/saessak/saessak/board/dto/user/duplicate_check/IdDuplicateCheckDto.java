package com.saessak.saessak.board.dto.user.duplicate_check;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


public record IdDuplicateCheckDto(
        @ApiModelProperty(value = "중복 검사할 아이디", dataType = "string", required = true, example = "saessak")
        String id
) {

}



