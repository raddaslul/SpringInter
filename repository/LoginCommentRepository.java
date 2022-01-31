package com.sparta.springinter.repository;

import com.sparta.springinter.domain.LoginComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginCommentRepository extends JpaRepository<LoginComment, Long> {
    List<LoginComment> findAllByOrderByModifiedAtDesc();


}
