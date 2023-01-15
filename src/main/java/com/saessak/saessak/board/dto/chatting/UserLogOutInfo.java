package com.saessak.saessak.board.dto.chatting;

import lombok.Builder;

public class UserLogOutInfo {

    String userId;
    String userName;

    @Builder
    public UserLogOutInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
