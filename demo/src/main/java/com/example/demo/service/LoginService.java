package com.example.demo.service;

import org.aspectj.bridge.context.PinpointingMessageHandler;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailService { 
    
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserById (String id) throws 
    
}
