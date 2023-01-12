package com.saessak.saessak.board.dto.chatting;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Chatting {

    String userId;
    String userName;
    Date sendTime;
    String message;

    @Builder
    public Chatting(
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
}