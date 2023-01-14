package com.saessak.saessak.board.service;

import com.saessak.saessak.board.dto.chatting.ChattingDateDto;
import com.saessak.saessak.board.dto.chatting.ChattingDto;
import com.saessak.saessak.board.repository.ChattingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class ChattingService {

    private final ChattingRepository repository;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

    public List<ChattingDto> fetchChattingList(ChattingDateDto chattingDateDto) {
//        List<Chatting> chattingList = repository.fetchChattingList(chattingDateDto.toEntity());
        List<ChattingDto> chattingList = new ArrayList<>();
        if (sdf.format(chattingDateDto.targetDate()).equals("20230101")) {
            chattingList.add(ChattingDto.builder().userName("새싹이").userId("saessak").sendTime(new Date()).message("테스트입니다.").build());

        }
        return chattingList;
//        return chattingList.stream().map(ChattingDto::new).toList();
    }
}
