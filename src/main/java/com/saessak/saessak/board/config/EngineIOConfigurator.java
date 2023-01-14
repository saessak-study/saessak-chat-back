package com.saessak.saessak.board.config;

import com.saessak.saessak.board.handler.EngineIoHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class EngineIOConfigurator implements WebSocketConfigurer {

    private final EngineIoHandler engineIoHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(engineIoHandler, "/socket.io/").addInterceptors(engineIoHandler).setAllowedOrigins("*");
        registry.addHandler(engineIoHandler, "/engine.io/").addInterceptors(engineIoHandler).setAllowedOrigins("*");
    }
}
