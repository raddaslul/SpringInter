package com.sparta.springinter.repository;

import com.sparta.springinter.domain.LoginBulletin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginBulletinRepository extends JpaRepository<LoginBulletin, Long> {
    List<LoginBulletin> findAllByOrderByModifiedAtDesc();

}