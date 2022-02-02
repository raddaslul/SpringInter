package com.sparta.springinter.repository;

import com.sparta.springinter.domain.LoginBulletin;
import com.sparta.springinter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoginBulletinRepository extends JpaRepository<LoginBulletin, Long> {
    List<LoginBulletin> findAllByOrderByModifiedAtDesc();

    Long deleteByBidAndUserId(Long bid, Long userId);
}