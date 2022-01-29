package com.sparta.springinter.service;

import com.sparta.springinter.repository.LoginBulletinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginBulletinService {

    private final LoginBulletinRepository loginBulletinRepository;
}
