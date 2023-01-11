package com.saessak.saessak.board.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ChattingDTO {

    String userId;
    String userName;
    Date sendTime;
    String message;

    @Builder
    public ChattingDTO(
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
