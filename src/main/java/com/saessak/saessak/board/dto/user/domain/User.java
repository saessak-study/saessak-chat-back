package com.saessak.saessak.board.dto.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_id")
    private String id;

    @Column(name = "user_pw")
    private String pw;

    @Column(name = "user_mail")
    private String mail;

}
