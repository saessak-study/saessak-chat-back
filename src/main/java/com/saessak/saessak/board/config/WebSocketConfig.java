package com.saessak.saessak.board.config;

import com.saessak.saessak.board.handler.ChattingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChattingHandler chattingHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chattingHandler, "/test/chat/{userId}")
                .setAllowedOriginPatterns("*");

        registry.addHandler(chattingHandler, "/chat/{userId}")
                .setAllowedOriginPatterns("*")
                .withSockJS();

    }
}
