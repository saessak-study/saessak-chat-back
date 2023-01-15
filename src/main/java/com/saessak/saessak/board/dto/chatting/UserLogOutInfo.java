package com.saessak.saessak.board.dto.chatting;

import lombok.Builder;

public class UserLogOutInfo {

    String userId;
    String userName;
    String message;

    @Builder
    public UserLogOutInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.message = userName + "님이 퇴장하셨습니다. Good Bye!";
    }
}
