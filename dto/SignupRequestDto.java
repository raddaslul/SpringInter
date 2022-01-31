package com.sparta.springinter.dto;

//import com.sparta.springinter.security.PasswordMatches;
import lombok.*;

import javax.validation.constraints.*;


@Setter
@Getter
@ToString
@NoArgsConstructor
//@PasswordMatches
public class SignupRequestDto {

//    @NotBlank(message = "닉네임을 입력해 주세요.")
//    @Size(min = 3, message = "닉네임은 3글자 이상이어야 합니다.")
    @Pattern(regexp="(?!.*[ㄱ-ㅎ])(?!.*[가-힣])(?!.*[!@#&()–[{}]:;',?/*~$^+=<>]).{3,}", message = "닉네임은 알파벳 대/소문자 혹은 숫자로 구성되어야 합니다.")
    private String username;

//    @NotBlank(message = "비밀번호를 입력해 주세요.")
//    @Size(min = 4, message = "비밀번호는 4글자 이상이어야 합니다.")
    private String password;
//    private String matchingPassword;
}