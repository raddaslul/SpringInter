package com.sparta.springinter.controller;

import com.sparta.springinter.domain.LoginBulletin;
import com.sparta.springinter.dto.LoginBulletinRequestDto;
import com.sparta.springinter.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginBulletinController {

    private final LoginRepository loginRepository;

    // 게시글 작성
    @PostMapping("/api/loginbulletins")
    public LoginBulletin createLoginBulletin(@RequestBody LoginBulletinRequestDto requestDto) {
        LoginBulletin loginBulletin = new LoginBulletin(requestDto);
        return loginRepository.save(loginBulletin);
    }

    // 게시글 조회
    @GetMapping("/api/loginbulletins")
    public List<LoginBulletin> getLoginBulletins() {
        return loginRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 삭제
    @DeleteMapping("/api/loginbulletins/{id}")
    public Long deleteLoginBulletin(@PathVariable Long id) {
        loginRepository.deleteById(id);
        return id;
    }
}
