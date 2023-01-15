package com.saessak.saessak.board.dto.chatting;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginInfo {

    String userId;
    String userName;

    @Builder
    public UserLoginInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

}
