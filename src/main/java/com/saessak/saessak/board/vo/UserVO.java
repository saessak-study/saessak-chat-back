package com.saessak.saessak.board.vo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "user_info")
public class UserVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_idx;
    private String user_name;
    private String user_id;
    private String user_pw;
    private String user_mail;

    @Builder
    public UserVO(String userName, String userId, String userPassword, String userMail) {
        user_name = userName;
        user_id = userId;
        user_pw = userPassword;
        user_mail = userMail;
    }
}
