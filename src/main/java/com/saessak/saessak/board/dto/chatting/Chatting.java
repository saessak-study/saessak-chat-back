package com.saessak.saessak.board.dto.chatting;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


@ToString
@Getter
@Setter
@Entity
@Table(name = "chatting_history")
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatting_idx")
    private Long chattingIdx;

    @Column(name = "send_user_id")
    private String userId;

    @Column(name = "send_user_name")
    private String userName;

    @Column(name = "chatting_send_time")
    Date sendTime;

    @Column(name = "message")
    String message;

}