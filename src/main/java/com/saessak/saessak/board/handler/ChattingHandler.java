package com.saessak.saessak.board.handler;

import com.google.gson.Gson;
import com.saessak.saessak.board.dto.chatting.ChattingDto;
import com.saessak.saessak.board.dto.chatting.UserLogOutInfo;
import com.saessak.saessak.board.dto.chatting.UserLoginInfo;
import com.saessak.saessak.board.dto.user.domain.User;
import com.saessak.saessak.board.service.ChattingService;
import com.saessak.saessak.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ChattingHandler extends TextWebSocketHandler {

    public static Map<WebSocketSession, User> userList = new HashMap<>();

    private final UserService userService;
    private final ChattingService chattingService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        User sendUser = userList.get(session);
        ChattingDto chat = ChattingDto.builder()
                .userId(sendUser.getId())
                .userName(sendUser.getName())
                .sendTime(new Date())
                .message(msg)
                .build();
        chattingService.saveChatting(chat);
        for (WebSocketSession sess : userList.keySet().stream().toList()) {
            if (sess != session) {
                sess.sendMessage(new TextMessage(new Gson().toJson(chat)));
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        URI sessionUri = session.getUri();
        if (sessionUri != null) {
            User currentUser = getUserFromSession(sessionUri);
            UserLoginInfo loginUser = UserLoginInfo.builder().userId(currentUser.getId()).userName(currentUser.getName()).build();
            for (WebSocketSession sess : userList.keySet().stream().toList()) {
                sess.sendMessage(new TextMessage(new Gson().toJson(loginUser)));
            }
            userList.put(session, currentUser);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        User logOutUser = userList.remove(session);
        if (logOutUser == null) return;
        UserLogOutInfo logOutInfo = UserLogOutInfo.builder().userId(logOutUser.getId()).userName(logOutUser.getName()).build();
        for (WebSocketSession sess : userList.keySet().stream().toList()) {
            sess.sendMessage(new TextMessage(new Gson().toJson(logOutInfo)));
        }
    }


    private User getUserFromSession(URI sessionUri) {
        String path = sessionUri.getPath();
        String[] pathSegment = path.split("/");
        String userId = "";
        boolean isChat = false;
        for (String segment : pathSegment) {
            if (isChat) {
                userId = segment;
                break;
            }
            if (segment.equals("chat")) isChat = true;
        }
        return userService.findUserById(userId);
    }
}
