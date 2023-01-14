package com.saessak.saessak;

import io.socket.engineio.server.EngineIoServer;
import io.socket.engineio.server.EngineIoServerOptions;
import io.socket.socketio.server.SocketIoServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SaessakApplication {

    @Bean
    public EngineIoServer engineIoServer() {
        EngineIoServerOptions options = EngineIoServerOptions.newFromDefault();
        options.setAllowedCorsOrigins(new String[]{"*"});
        options.setPingTimeout(30000);
        EngineIoServer engineIoServer = new EngineIoServer(options);
        SocketIoServer socketIoServer = new SocketIoServer(engineIoServer);
        engineIoServer.on("connection", args -> {
            System.out.println(args);
        }).on("test", args -> {
            System.out.println(args);
        });
        return engineIoServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(SaessakApplication.class, args);
    }

}
