package com.example.sskcaht.board.dto;

import com.example.sskcaht.board.message.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefaultRes {
    private int statusCode;
    private String responseMessage;
 

    public DefaultRes(final int statusCode, final String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
    }

    public static DefaultRes res(final int statusCode, final String responseMessage) {
        return new DefaultRes(statusCode, responseMessage);
    }


}
