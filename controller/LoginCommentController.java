package com.sparta.springinter.controller;

import com.sparta.springinter.domain.LoginBulletin;
import com.sparta.springinter.domain.LoginComment;
import com.sparta.springinter.domain.User;
import com.sparta.springinter.dto.LoginCommentRequestDto;
import com.sparta.springinter.repository.LoginBulletinRepository;
import com.sparta.springinter.repository.LoginCommentRepository;
import com.sparta.springinter.security.UserDetailsImpl;
import com.sparta.springinter.service.LoginCommentService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logincomments")
public class LoginCommentController {

    private final LoginBulletinRepository loginBulletinRepository;
    private final LoginCommentRepository loginCommentRepository;
    private final LoginCommentService loginCommentService;

//     댓글 작성
    @PostMapping("/loginbulletins/{bid}")
    public Boolean createLoginComment(@PathVariable Long bid, @RequestBody LoginCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        LoginBulletin loginBulletin = loginBulletinRepository.findById(bid)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다." + bid));
        User user = userDetails.getUser();
        LoginComment loginComment = loginCommentService.createLoginComment(requestDto, loginBulletin, user);
        loginCommentRepository.save(loginComment);
        return true;
    }

    // 댓글 조회
//    @GetMapping("/logincomments")
//    public Optional<LoginComment> getLoginComments(@PathVariable Long bid) {
//        return loginCommentRepository.findAllById(bid);
//    }

    // 댓글 수정
    @PutMapping("/{userid}/loginbulletins/{bid}")
    public Long updateLoginCommnet(@PathVariable Long userId, @RequestBody LoginCommentRequestDto requestDto) {
        loginCommentService.update(userId, requestDto);
        return userId;
    }


    // 댓글 삭제
    @DeleteMapping("/{id}/loginbulletins/{bid}")
    public Boolean deleteLoginComment(@PathVariable Long id, @PathVariable String bid) {
        loginCommentRepository.deleteById(id);
        return true;
    }
}
