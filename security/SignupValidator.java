package com.sparta.springinter.security;


import com.sparta.springinter.dto.SignupRequestDto;
import com.sparta.springinter.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

//@Component
//public class PasswordValidator implements ConstraintValidator<PasswordMatches, Object> {
//    @Override
//    public void initialize(PasswordMatches constraintAnnotation) {
//    }
//    @Override
//    public boolean isValid(Object obj, ConstraintValidatorContext context){
//        SignupRequestDto signupRequestDto = (SignupRequestDto) obj;
//        return signupRequestDto.getPassword().equals(signupRequestDto.getMatchingPassword());
//    }
//}