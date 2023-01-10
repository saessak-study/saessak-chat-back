package com.saessak.saessak.board.controller;

import com.saessak.saessak.board.dto.DefaultRes;
import com.saessak.saessak.board.dto.UserSignUpDto;
import com.saessak.saessak.board.message.ResponseMessage;
import com.saessak.saessak.board.message.StatusCode;
import com.saessak.saessak.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<DefaultRes> signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER), HttpStatus.OK);

    }

}
