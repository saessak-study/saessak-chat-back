package com.saessak.saessak.board.dto.user;

import com.saessak.saessak.board.dto.user.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserOnlineStatus {

    String userId;
    String userName;
    Boolean isOnline;

    @Builder
    public UserOnlineStatus(User user, Boolean isOnline) {
        userId = user.getId();
        userName = user.getName();
        this.isOnline = isOnline;
    }
}
