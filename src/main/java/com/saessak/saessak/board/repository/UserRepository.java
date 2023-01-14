package com.saessak.saessak.board.repository;

import com.saessak.saessak.board.dto.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(String id);

    User findUserByIdAndPw(String id, String password);

    User findUserByNameAndMail(String name, String mail);

    User findUserByNameAndMailAndId(String name, String mail, String id);
}
