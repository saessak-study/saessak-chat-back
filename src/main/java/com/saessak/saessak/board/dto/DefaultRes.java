package com.saessak.saessak.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefaultRes<T> {
    private int statusCode;
    private T responseMessage;


    public DefaultRes(final int statusCode, final T responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
    }

    public static <T> DefaultRes<T> res(final int statusCode, final T responseMessage) {
        return new DefaultRes<>(statusCode, responseMessage);
    }


}
