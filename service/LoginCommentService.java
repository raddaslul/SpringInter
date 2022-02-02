package com.sparta.springinter.service;

import com.sparta.springinter.domain.LoginBulletin;
import com.sparta.springinter.domain.LoginComment;
import com.sparta.springinter.dto.LoginCommentRequestDto;
import com.sparta.springinter.repository.LoginCommentRepository;
import com.sparta.springinter.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class LoginCommentService {

    private final LoginCommentRepository loginCommentRepository;

    @Transactional
    public Boolean update(Long cid, Long userId, LoginCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        LoginComment loginComment = loginCommentRepository.findById(cid).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );loginComment.update(requestDto);
        return true;
    }

    public LoginComment createLoginComment(LoginCommentRequestDto requestDto, LoginBulletin loginBulletin, Long bid, Long userId, String username) {
        LoginComment loginComment = new LoginComment(requestDto, loginBulletin, bid, userId, username);
        loginCommentRepository.save(loginComment);
        return  loginComment;
    }
}
