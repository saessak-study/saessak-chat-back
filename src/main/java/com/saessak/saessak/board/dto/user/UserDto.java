package com.saessak.saessak.board.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDto {
    private String name;
    private String id;
    private String pw;
    private String mail;

    @Builder
    public UserDto(User user) {
        this.name = user.getName();
        this.id = user.getId();
        this.pw = user.getPw();
        this.mail = user.getMail();
    }

    public User toEntity() {
        return User.builder().name(name).id(id).pw(pw).mail(mail).build();
    }


}
