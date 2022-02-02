package com.sparta.springinter.service;

import com.sparta.springinter.domain.LoginBulletin;
import com.sparta.springinter.domain.User;
import com.sparta.springinter.dto.LoginBulletinRequestDto;
import com.sparta.springinter.repository.LoginBulletinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginBulletinService {

    private final LoginBulletinRepository loginBulletinRepository;

        public LoginBulletin createLoginBulletin(LoginBulletinRequestDto requestDto, Long userId, User user) {
            LoginBulletin loginBulletin = new LoginBulletin(requestDto, user, userId);
            loginBulletinRepository.save(loginBulletin);
            return loginBulletin;
    }
}
