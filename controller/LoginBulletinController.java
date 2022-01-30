package com.sparta.springinter.controller;

import com.sparta.springinter.domain.LoginBulletin;
import com.sparta.springinter.dto.LoginBulletinRequestDto;
import com.sparta.springinter.repository.LoginBulletinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginBulletinController {

    private final LoginBulletinRepository loginBulletinRepository;


    // 게시글 작성
    @PostMapping("/api/loginbulletins")
    public LoginBulletin createLoginBulletin(@RequestBody LoginBulletinRequestDto requestDto) {
        LoginBulletin loginBulletin = new LoginBulletin(requestDto);
        return loginBulletinRepository.save(loginBulletin);
    }

    // 게시글 조회
    @GetMapping("/api/loginbulletins")
    public List<LoginBulletin> getLoginBulletins() {
        return loginBulletinRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 상세 조회
    @GetMapping("/api/loginbulletins/{bid}")
    public Optional<LoginBulletin> getLoginBulletinsDetail(@PathVariable Long bid) {
        return loginBulletinRepository.findById(bid);
    }

    // 게시글 삭제
    @DeleteMapping("/api/loginbulletins/{bid}/logincomments")
    public Long deleteLoginBulletin(@PathVariable Long bid) {
        loginBulletinRepository.deleteById(bid);
        return bid;
    }
}
