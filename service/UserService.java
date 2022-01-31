package com.sparta.springinter.service;


import com.sparta.springinter.domain.User;
import com.sparta.springinter.dto.SignupRequestDto;
import com.sparta.springinter.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void registerUser(SignupRequestDto signuprequestDto) {
//        // 회원 ID 중복 확인
        String username = signuprequestDto.getUsername();
//        Optional<User> found = userRepository.findByUsername(username);
//        if (found.isPresent()) {
//            throw new IllegalArgumentException("중복된 닉네임입니다");
//        }


        // 패스워드 암호화
        String password = passwordEncoder.encode(signuprequestDto.getPassword());

        User user = new User(username, password);
        userRepository.save(user);
    }



    // 회원가입 시 유효성 체크
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    public Boolean duple(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        try{
            if(user.getUsername().equals(username)){
                System.out.println("중복이다.");
                return false;
            }
        } catch (NullPointerException e){
            System.out.println("중복아니다.");
            return true;
        }
        System.out.println("들어오니?");
        return true;
    }
}