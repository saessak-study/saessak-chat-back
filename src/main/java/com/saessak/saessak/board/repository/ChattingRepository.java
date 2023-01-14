package com.saessak.saessak.board.repository;

import com.saessak.saessak.board.dto.chatting.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ChattingRepository extends JpaRepository<Chatting, Long> {

    List<Chatting> findAllBySendTimeBetween(Date startDate, Date endDate);

}
