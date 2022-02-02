package com.sparta.springinter.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long userId;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) // 정보를 받을 때는 Enum 값으로 받지만
                                         // db에 갈때는 Spring Jpa에 의해 자동으로 String으로 변환됨
    private UserRoleEnum role;

    @Column(unique = true)
    private Long kakaoId;

    @OrderBy("bid desc ")
    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<LoginBulletin> loginBulletins;

    // 일반 로그인 회원
    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.kakaoId = null;
        this.role = role;
    }

    // 카카로 로그인 회원
    public User(String username, String password, Long kakaoId, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.kakaoId = kakaoId;
        this.role=role;
    }
}