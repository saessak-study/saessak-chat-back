package com.saessak.saessak.board.controller;

import com.saessak.saessak.board.dto.DefaultRes;
import com.saessak.saessak.board.dto.chatting.ChattingDateDto;
import com.saessak.saessak.board.dto.chatting.ChattingDto;
import com.saessak.saessak.board.message.ResponseMessage;
import com.saessak.saessak.board.message.StatusCode;
import com.saessak.saessak.board.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChattingController {

    private final ChattingService chattingService;

    @GetMapping("/chat-history")
    public ResponseEntity<DefaultRes<Object>> chattingList(@RequestBody ChattingDateDto chattingDateDto) {
        List<ChattingDto> chattingList = chattingService.fetchChattingList(chattingDateDto);
        if (chattingList.isEmpty()) {
            return new ResponseEntity<>(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.CHATTING_LOG_NOT_FOUND), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(DefaultRes.res(StatusCode.OK, chattingList), HttpStatus.OK);
        }
    }

}
