package com.saessak.saessak.board.repository;

import com.saessak.saessak.board.dto.user.User;
import com.saessak.saessak.board.dto.user.id_search.IdSearchKeyInfo;
import com.saessak.saessak.board.dto.user.login.LoginInfo;
import com.saessak.saessak.board.dto.user.password_search.PasswordSearchKeyInfo;
import com.saessak.saessak.board.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface UserRepository extends JpaRepository<UserVO, Long> {
//
//    void save(User user);
//
//    User findById(String id);
//
//    User findByNameAndEmail(IdSearchKeyInfo idSearchKeyInfo);
//
//    User findByNameAndEmailAndId(String name, String mail, String id);
//
//    User findUserByIdAndPassword(LoginInfo loginInfo);
//}
