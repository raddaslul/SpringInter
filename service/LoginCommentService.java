package com.sparta.springinter.service;

import com.sparta.springinter.domain.LoginBulletin;
import com.sparta.springinter.domain.LoginComment;
import com.sparta.springinter.dto.LoginCommentRequestDto;
import com.sparta.springinter.repository.LoginBulletinRepository;
import com.sparta.springinter.repository.LoginCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginCommentService {

    private final LoginCommentRepository loginCommentRepository;
    private final LoginBulletinRepository loginBulletinRepository;

//    @Transactional
//    public Long update(Long id, LoginCommentRequestDto requestDto) {
//        LoginComment loginComment = loginCommentRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        loginComment.update(requestDto);
//        return loginComment.getId();
//    }


}
