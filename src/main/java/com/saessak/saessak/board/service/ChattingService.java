package com.saessak.saessak.board.service;

import com.saessak.saessak.board.dto.chatting.Chatting;
import com.saessak.saessak.board.dto.chatting.ChattingDateDto;
import com.saessak.saessak.board.dto.chatting.ChattingDto;
import com.saessak.saessak.board.repository.ChattingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChattingService {

    private final ChattingRepository chattingRepository;


    public List<ChattingDto> fetchChattingList(ChattingDateDto chattingDateDto) {
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(chattingDateDto.targetDate());
        endDate.add(Calendar.DAY_OF_MONTH, 1);
        List<Chatting> chattingList = chattingRepository.findAllBySendTimeBetween(chattingDateDto.targetDate(), endDate.getTime());
        return chattingList.stream().map(ChattingDto::new).toList();
    }

    public void saveChatting(ChattingDto chattingDto) {
        Chatting chatting = new Chatting();
        chatting.setUserId(chattingDto.getUserId());
        chatting.setUserName(chattingDto.getUserName());
        chatting.setMessage(chattingDto.getMessage());
        chatting.setSendTime(chattingDto.getSendTime());
        chattingRepository.save(chatting);
    }

    public List<ChattingDto> getAll() {
        List<Chatting> chattingList = chattingRepository.findAll();
        return chattingList.stream().map(ChattingDto::new).toList();
    }
}
