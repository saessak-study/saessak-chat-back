package com.saessak.saessak.board.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.saessak.saessak.board.dto.chatting.ChattingDto;
import com.saessak.saessak.board.dto.user.domain.User;
import com.saessak.saessak.board.service.ChattingService;
import com.saessak.saessak.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SocketIOConfig {

    @Value("${socket.host}")
    private String socketHost;
    @Value("${socket.port}")
    private int socketPort;
    private UserService userService;
    private ChattingService chattingService;
    private SocketIOServer server;

    public static final Map<SocketIOClient, User> clientList = new HashMap<>();

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(socketHost);
        config.setPort(socketPort);
        server = new SocketIOServer(config);
        server.start();

        server.addConnectListener(onUserConnectListener);
        server.addDisconnectListener(onUserDisconnectListener);
        server.addEventListener("log-in", String.class, onUserLoginListener);
        server.addEventListener("chat", String.class, onChattingListener);
        return server;
    }

    @PreDestroy
    public void stopSocketIOServer() {
        this.server.stop();
    }


    private final ConnectListener onUserConnectListener = client -> client.sendEvent("connected", "ok");
    private final DisconnectListener onUserDisconnectListener = client -> {
        User removeTarget = clientList.remove(client);
        for (SocketIOClient socket : clientList.keySet()) {
            socket.sendEvent("log-out", removeTarget.getName());
        }
    };
    private final DataListener<String> onUserLoginListener = (client, data, ackSender) -> {
        User user = userService.findUserById(data);
        for (SocketIOClient socket : clientList.keySet()) {
            socket.sendEvent("log-in", user.getName());
        }
        clientList.put(client, user);
    };

    private final DataListener<String> onChattingListener = (client, data, ackSender) -> {
        User sendUser = clientList.get(client);
        ChattingDto chatting = ChattingDto.builder()
                .userId(sendUser.getId())
                .userName(sendUser.getName())
                .sendTime(new Date())
                .message(data)
                .build();
        chattingService.saveChatting(chatting);
        for (SocketIOClient socket : clientList.keySet()) {
            if (socket != client) {
                socket.sendEvent("chatting", chatting);
            }
        }
    };
}
