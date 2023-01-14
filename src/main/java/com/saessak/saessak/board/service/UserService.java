package com.saessak.saessak.board.service;

import com.saessak.saessak.board.dto.user.domain.User;
import com.saessak.saessak.board.dto.user.dto.SignupDto;
import com.saessak.saessak.board.dto.user.duplicate_check.IdDuplicateCheckDto;
import com.saessak.saessak.board.dto.user.id_search.IdSearchRequestDto;
import com.saessak.saessak.board.dto.user.login.LoginDto;
import com.saessak.saessak.board.dto.user.password_search.PasswordSearchRequestDto;
import com.saessak.saessak.board.dto.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
//        User foundedUser = repository.findById(idDuplicateCheckDto.id());
//        return foundedUser != null;
        return idDuplicateCheckDto.id().equals("saessak");
    }

    public String findId(IdSearchRequestDto idSearchRequestDto) {
//        User foundedUser = repository.findByNameAndEmail(idSearchRequestDto.toEntity());
//        if (foundedUser == null) return null;
//        else return foundedUser.getId();
        if (idSearchRequestDto.name().equals("김새싹") && idSearchRequestDto.email().equals("saessak@study.com"))
            return "saessak";
        else return null;
    }

    public String findPassword(PasswordSearchRequestDto passwordSearchRequestDto) {
//        User foundedUser = repository.findByNameAndEmailAndId(passwordSearchRequestDto.toEntity());
//        if (foundedUser == null) return null;
//        else return foundedUser.getPw();

        if (passwordSearchRequestDto.name().equals("김새싹") && passwordSearchRequestDto.email().equals("saessak@study.com") && passwordSearchRequestDto.id().equals("saessak"))
            return "study";
        else return null;
    }

    public Boolean userExists(LoginDto loginDto) {
//        User findUser = repository.findUserByIdAndPassword(loginDto.toEntity());
//        return findUser != null;
        return loginDto.id().equals("saessak") && loginDto.password().equals("study");
    }

}
