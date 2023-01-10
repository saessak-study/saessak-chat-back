package com.example.sskcaht.board.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sskcaht.board.dto.DefaultRes;
import com.example.sskcaht.board.dto.UserDto;
import com.example.sskcaht.board.dto.UserSignUpDto;
import com.example.sskcaht.board.message.ResponseMessage;
import com.example.sskcaht.board.message.StatusCode;
import com.example.sskcaht.board.service.UserService;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER), HttpStatus.OK);
       
    }
    
}
