package com.saessak.saessak.board.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.saessak.saessak.board.dto.ChattingDTO;
import com.saessak.saessak.board.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class SocketIOConfig {

    @Value("${socket.host}")
    private String socketHost;
    @Value("${socket.port}")
    private int socketPort;
    private SocketIOServer server;

    private static final Map<SocketIOClient, User> clientList = new HashMap<>();

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
        User user = User.builder().name("유저-" + client.getSessionId()).id(data).pw("pw").mail("mail").build();
        for (SocketIOClient socket : clientList.keySet()) {
            socket.sendEvent("log-in", user);
        }
        clientList.put(client, user);
    };

    private final DataListener<String> onChattingListener = (client, data, ackSender) -> {
        User sendUser = clientList.get(client);
        ChattingDTO chatting = ChattingDTO.builder()
                .userId(sendUser.getId())
                .userName(sendUser.getName())
                .sendTime(new Date())
                .message(data)
                .build();
        for (SocketIOClient socket : clientList.keySet()) {
            if (socket != client) {
                socket.sendEvent("chatting", chatting);
            }
        }
    };
}
