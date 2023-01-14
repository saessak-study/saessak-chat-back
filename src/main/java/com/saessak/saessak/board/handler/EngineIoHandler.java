package com.saessak.saessak.board.handler;

import io.socket.engineio.server.EngineIoServer;
import io.socket.engineio.server.EngineIoWebSocket;
import io.socket.engineio.server.utils.ParseQS;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.*;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class EngineIoHandler implements HandshakeInterceptor, WebSocketHandler {

    private static final String ATTRIBUTE_ENGINE_IO_BRIDGE = "engineIo.bridge";
    private static final String ATTRIBUTE_ENGINE_IO_QUERY = "engineIo.query";

    private final EngineIoServer engineIoServer;

    @RequestMapping(
            value = "/engine.io/*",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS},
            headers = "Connection!=Upgrade")
    public void httpHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        engineIoServer.handleRequest(request, response);
    }

    @RequestMapping(
            value = "/socket.io/*",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS},
            headers = "Connection!=Upgrade")
    public void httpHandler2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        engineIoServer.handleRequest(request, response);
    }

    /* HandshakeInterceptor */

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws IOException {
        attributes.put(ATTRIBUTE_ENGINE_IO_QUERY, request.getURI().getQuery());
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }

    /* WebSocketHandler */

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        final EngineIoSpringWebSocket websocket = new EngineIoSpringWebSocket(webSocketSession);
        webSocketSession.getAttributes().put(ATTRIBUTE_ENGINE_IO_BRIDGE, websocket);
        engineIoServer.handleWebSocket(websocket);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        ((EngineIoSpringWebSocket) webSocketSession.getAttributes().get(ATTRIBUTE_ENGINE_IO_BRIDGE))
                .afterConnectionClosed(closeStatus);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {
        ((EngineIoSpringWebSocket) webSocketSession.getAttributes().get(ATTRIBUTE_ENGINE_IO_BRIDGE))
                .handleMessage(webSocketMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {
        ((EngineIoSpringWebSocket) webSocketSession.getAttributes().get(ATTRIBUTE_ENGINE_IO_BRIDGE))
                .handleTransportError(throwable);
    }

    private static final class EngineIoSpringWebSocket extends EngineIoWebSocket {

        private final WebSocketSession mSession;
        private final Map<String, String> mQuery;

        EngineIoSpringWebSocket(WebSocketSession session) {
            mSession = session;

            final String queryString = (String) mSession.getAttributes().get(ATTRIBUTE_ENGINE_IO_QUERY);
            if (queryString != null) {
                mQuery = ParseQS.decode(queryString);
            } else {
                mQuery = new HashMap<>();
            }
        }

        /* EngineIoWebSocket */

        @Override
        public Map<String, String> getQuery() {
            return mQuery;
        }

        @Override
        public Map<String, List<String>> getConnectionHeaders() {
            return null;
        }

        @Override
        public void write(String message) throws IOException {
            mSession.sendMessage(new TextMessage(message));
        }

        @Override
        public void write(byte[] message) throws IOException {
            mSession.sendMessage(new BinaryMessage(message));
        }

        @Override
        public void close() {
            try {
                mSession.close();
            } catch (IOException ignore) {
            }
        }

        /* WebSocketHandler */

        void afterConnectionClosed(CloseStatus closeStatus) {
            emit("close");
        }

        void handleMessage(WebSocketMessage<?> message) {
            if (message.getPayload() instanceof String || message.getPayload() instanceof byte[]) {
                emit("message", (Object) message.getPayload());
            } else {
                throw new RuntimeException(String.format(
                        "Invalid message type received: %s. Expected String or byte[].",
                        message.getPayload().getClass().getName()));
            }
        }

        void handleTransportError(Throwable exception) {
            emit("error", "write error", exception.getMessage());
        }
    }
}