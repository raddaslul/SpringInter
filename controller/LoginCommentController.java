package com.sparta.springinter.controller;

import com.sparta.springinter.domain.LoginBulletin;
import com.sparta.springinter.domain.LoginComment;
import com.sparta.springinter.dto.LoginCommentRequestDto;
import com.sparta.springinter.repository.LoginBulletinRepository;
import com.sparta.springinter.repository.LoginCommentRepository;
import com.sparta.springinter.service.LoginCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loginbulletins/{bid}")
public class LoginCommentController {

    private final LoginBulletinRepository loginBulletinRepository;
    private final LoginCommentRepository loginCommentRepository;
    private final LoginCommentService loginCommentService;

    // 댓글 작성
    @PostMapping("/logincomments")
    public LoginComment createLoginComment(@PathVariable Long bid, @RequestBody LoginComment loginComment) {
        LoginBulletin loginBulletin = loginBulletinRepository.findById(bid)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다." + bid));
        loginComment.save(loginBulletin);

        return loginCommentRepository.save(loginComment);
    }

    // 댓글 조회
//    @GetMapping("/logincomments")
//    public List<LoginComment> getLoginComments(@PathVariable String bid) {
//        return loginCommentRepository.findAllByOrderByModifiedAtDesc();
//    }

    // 댓글 수정
    @PutMapping("/logincomments/{id}")
    public Long updateLoginCommnet(@PathVariable Long id, @RequestBody LoginCommentRequestDto requestDto, @PathVariable String bid) {
        loginCommentService.update(id, requestDto);
        return id;
    }


    // 댓글 삭제
    @DeleteMapping("/logincomments/{id}")
    public Long deleteLoginComment(@PathVariable Long id, @PathVariable String bid) {
        loginCommentRepository.deleteById(id);
        return id;
    }
}
