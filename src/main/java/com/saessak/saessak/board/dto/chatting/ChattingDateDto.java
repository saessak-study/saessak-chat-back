package com.saessak.saessak.board.dto.chatting;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record ChattingDateDto(
        @ApiModelProperty(value = "채팅목록을 불러올 날짜", dataType = "Date", required = true, example = "2023-01-10")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date targetDate
) {
    public ChattingDate toEntity() {
        return ChattingDate.builder().date(targetDate).build();
    }
}