package com.saessak.saessak.board.controller;

import com.saessak.saessak.board.dto.DefaultRes;
import com.saessak.saessak.board.dto.user.UserOnlineStatus;
import com.saessak.saessak.board.dto.user.domain.User;
import com.saessak.saessak.board.dto.user.dto.SignupDto;
import com.saessak.saessak.board.dto.user.duplicate_check.IdDuplicateCheckDto;
import com.saessak.saessak.board.dto.user.id_search.IdSearchRequestDto;
import com.saessak.saessak.board.dto.user.id_search.IdSearchResponseDto;
import com.saessak.saessak.board.dto.user.login.LoginDto;
import com.saessak.saessak.board.dto.user.password_search.PasswordSearchRequestDto;
import com.saessak.saessak.board.dto.user.password_search.PasswordSearchResponseDto;
import com.saessak.saessak.board.handler.ChattingHandler;
import com.saessak.saessak.board.message.ResponseMessage;
import com.saessak.saessak.board.message.StatusCode;
import com.saessak.saessak.board.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = ResponseMessage.CREATED_USER)
    })
    @PutMapping("/sign-up")
    public ResponseEntity<DefaultRes<Object>> signUp(@RequestBody SignupDto signupDto) {
        userService.createUser(signupDto);
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER), HttpStatus.OK);
    }

    @ApiOperation(value = "아이디 중복 체크")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = ResponseMessage.AVAILABLE_ID),
            @ApiResponse(code = StatusCode.CONFLICT, message = ResponseMessage.CONFLICT_ID)
    })
    @PostMapping("/id-duplicate-check")
    public ResponseEntity<DefaultRes<String>> idCheck(@RequestBody IdDuplicateCheckDto idDuplicateCheckDto) {
        if (userService.isIdDuplicated(idDuplicateCheckDto)) {
            return new ResponseEntity<>(DefaultRes.res(StatusCode.CONFLICT, ResponseMessage.CONFLICT_ID), HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.AVAILABLE_ID), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "아이디 찾기")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = ResponseMessage.EMPTY_MESSAGE, response = IdSearchResponseDto.class),
            @ApiResponse(code = StatusCode.NOT_FOUND, message = ResponseMessage.USER_NOT_FOUND)
    })
    @PostMapping("/find-id")
    public ResponseEntity<DefaultRes<Object>> findId(@RequestBody IdSearchRequestDto idSearchRequestDto) {
        String userId = userService.findId(idSearchRequestDto);
        if (userId == null) {
            return new ResponseEntity<>(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, new IdSearchResponseDto(userId)), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "패스워드 찾기")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = ResponseMessage.EMPTY_MESSAGE, response = PasswordSearchResponseDto.class),
            @ApiResponse(code = StatusCode.NOT_FOUND, message = ResponseMessage.USER_NOT_FOUND)
    })
    @PostMapping("/find-password")
    public ResponseEntity<DefaultRes<Object>> findPassword(@RequestBody PasswordSearchRequestDto passwordSearchRequestDto) {
        String password = userService.findPassword(passwordSearchRequestDto);
        if (password == null) {
            return new ResponseEntity<>(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, new PasswordSearchResponseDto(password)), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "로그인")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = ResponseMessage.LOGIN_SUCCESS),
            @ApiResponse(code = StatusCode.NOT_FOUND, message = ResponseMessage.USER_NOT_FOUND)
    })
    @PostMapping("/login")
    public ResponseEntity<DefaultRes<String>> login(@RequestBody LoginDto loginDto) {
        if (userService.userExists(loginDto)) {
            return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "현재 접속중인 유저 리스트 조회")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = ResponseMessage.EMPTY_MESSAGE, response = UserOnlineStatus.class, responseContainer = "List")
    })
    @GetMapping("/online-user")
    public ResponseEntity<DefaultRes<List<UserOnlineStatus>>> onlineUserList() {
        List<User> loginUserList = ChattingHandler.userList.values().stream().toList();
        List<User> users = userService.getAll();
        List<UserOnlineStatus> onlineStatuses = users.stream().map(user -> {
            boolean isOnline = false;
            for (User onlineUser : loginUserList) {
                if (user.getId().equals(onlineUser.getId())) {
                    isOnline = true;
                    break;
                }
            }
            return new UserOnlineStatus(user, isOnline);
        }).toList();
        return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, onlineStatuses), HttpStatus.OK);
    }
}
