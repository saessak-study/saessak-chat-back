package com.saessak.saessak.board.dto.chatting;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginInfo {

    String userId;
    String userName;
    String message;

    @Builder
    public UserLoginInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.message= userName + "님이 입장하셨습니다. Hello!";
    }

}
