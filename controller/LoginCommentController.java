package com.sparta.springinter.controller;

import com.sparta.springinter.domain.LoginBulletin;
import com.sparta.springinter.domain.LoginComment;
import com.sparta.springinter.dto.LoginCommentRequestDto;
import com.sparta.springinter.repository.LoginBulletinRepository;
import com.sparta.springinter.repository.LoginCommentRepository;
import com.sparta.springinter.security.UserDetailsImpl;
import com.sparta.springinter.service.LoginCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


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
        Long userId = userDetails.getUser().getUserId();
        String username = userDetails.getUser().getUsername();

        LoginComment loginComment = loginCommentService.createLoginComment(requestDto, loginBulletin, bid, userId, username);
        loginCommentRepository.save(loginComment);
        return true;
    }

    // 댓글 조회
    @GetMapping("/loginbulletins/{bid}")
    public List<LoginComment> getLoginComments(@PathVariable Long bid) {
        System.out.println(loginCommentRepository);
        return loginCommentRepository.findAllByBidOrderByModifiedAtDesc(bid);
    }

    // 댓글 수정
    @PutMapping("/{cid}/loginbulletins/{userId}")
    public Boolean updateLoginComment(
            @PathVariable Long cid, @PathVariable Long userId, @RequestBody LoginCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long loginId = userDetails.getUser().getUserId();
        if (userId == loginId) {
            loginCommentService.update(cid, userId, requestDto, userDetails);
            return true;
        } else {
            return false;
        }
    }


    // 댓글 삭제
    @Transactional
    @DeleteMapping("/{cid}/loginbulletins/{userId}")
    public Boolean deleteLoginComment(@PathVariable Long cid, @PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long loginId = userDetails.getUser().getUserId();
        if(userId == loginId) {
            loginCommentRepository.deleteByCidAndUserId(cid, userId);
            return true;
        } else {
            return false;
        }
    }
}
