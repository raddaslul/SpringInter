package com.sparta.springinter.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.springinter.dto.SignupRequestDto;
import com.sparta.springinter.service.KakaoUserService;
import com.sparta.springinter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "/signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid SignupRequestDto requestDto, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("requestDto", requestDto);

            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/signup";
        }

        else if(userService.duple(requestDto.getUsername())){
            userService.registerUser(requestDto);

        } else {
            return "/signup";
        }
        return "redirect:/user/login";
    }

    // 회원 아이디 중복처리
    @ResponseBody
    @GetMapping("/user/duple/{username}")
    public Boolean duple(@PathVariable String username){
        return userService.duple(username);
    }


    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}