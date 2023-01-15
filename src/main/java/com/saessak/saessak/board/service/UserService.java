package com.saessak.saessak.board.service;

import com.saessak.saessak.board.dto.user.domain.User;
import com.saessak.saessak.board.dto.user.dto.SignupDto;
import com.saessak.saessak.board.dto.user.duplicate_check.IdDuplicateCheckDto;
import com.saessak.saessak.board.dto.user.id_search.IdSearchRequestDto;
import com.saessak.saessak.board.dto.user.login.LoginDto;
import com.saessak.saessak.board.dto.user.password_search.PasswordSearchRequestDto;
import com.saessak.saessak.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void createUser(SignupDto signupDto) {
        User user = new User();
        user.setName(signupDto.name());
        user.setId(signupDto.id());
        user.setPw(signupDto.pw());
        user.setMail(signupDto.mail());
        userRepository.save(user);
    }

    public Boolean isIdDuplicated(IdDuplicateCheckDto idDuplicateCheckDto) {
        return userRepository.findById(idDuplicateCheckDto.id()) != null;
    }

    public String findId(IdSearchRequestDto idSearchRequestDto) {
        User foundedUser = userRepository.findUserByNameAndMail(idSearchRequestDto.name(), idSearchRequestDto.email());
        if (foundedUser == null) return null;
        else return foundedUser.getId();
    }

    public String findPassword(PasswordSearchRequestDto passwordSearchRequestDto) {
        User foundedUser = userRepository.findUserByNameAndMailAndId(passwordSearchRequestDto.name(), passwordSearchRequestDto.email(), passwordSearchRequestDto.id());
        if (foundedUser == null) return null;
        else return foundedUser.getPw();
    }

    public Boolean userExists(LoginDto loginDto) {
        User findUser = userRepository.findUserByIdAndPw(loginDto.id(), loginDto.password());
        return findUser != null;
    }

    public User findUserById(String id) {
        return userRepository.findById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
