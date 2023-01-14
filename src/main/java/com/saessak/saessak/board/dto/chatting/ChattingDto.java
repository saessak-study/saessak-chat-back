package com.saessak.saessak.board.dto.chatting;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ChattingDto {

    String userId;
    String userName;
    Date sendTime;
    String message;

    @Builder
    public ChattingDto(
            String userId,
            String userName,
            Date sendTime,
            String message
    ) {
        this.userId = userId;
        this.userName = userName;
        this.sendTime = sendTime;
        this.message = message;
    }

    public ChattingDto(Chatting chatting) {
        this.userId = chatting.getUserId();
        this.userName = chatting.getUserName();
        this.sendTime = chatting.getSendTime();
        this.message = chatting.getMessage();
    }
}
