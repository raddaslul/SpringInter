package com.sparta.springinter.controller;

import com.sparta.springinter.domain.LoginBulletin;
import com.sparta.springinter.domain.User;
import com.sparta.springinter.dto.LoginBulletinRequestDto;
import com.sparta.springinter.repository.LoginBulletinRepository;
import com.sparta.springinter.security.UserDetailsImpl;
import com.sparta.springinter.service.LoginBulletinService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginBulletinController {

    private final LoginBulletinRepository loginBulletinRepository;
    private final LoginBulletinService loginBulletinService;


    // 게시글 작성
//    @Secured(UserRoleEnum.Authority.USER)
    @PostMapping("/api/write/loginbulletins")
    public Boolean createLoginBulletin(@RequestBody LoginBulletinRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Long userId = userDetails.getUser().getUserId();

        LoginBulletin loginBulletin = loginBulletinService.createLoginBulletin(requestDto, userId, user);
        loginBulletinRepository.save(loginBulletin);
        return true;
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
    @Transactional
    @DeleteMapping("/api/delete/{userId}/loginbulletins/{bid}")
    public Boolean deleteLoginBulletin(@PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long bid) {
        Long loginId = userDetails.getUser().getUserId();
        if (userId == loginId) {
            loginBulletinRepository.deleteByBidAndUserId(bid, userId);
            return true;
        } else {
            return false;
        }
    }
}
