package com.saessak.saessak.board.dto.chatting;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ChattingDate {

    Date date;

    @Builder
    public ChattingDate(Date date) {
        this.date = date;
    }
}
