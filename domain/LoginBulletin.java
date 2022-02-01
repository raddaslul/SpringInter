package com.sparta.springinter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.springinter.dto.LoginBulletinRequestDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class LoginBulletin extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long bid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @OrderBy("id desc")
    @JsonIgnoreProperties({"loginBulletin"})
    @OneToMany(mappedBy = "loginBulletin", cascade = CascadeType.REMOVE)
    private List<LoginComment> loginComments;



    public LoginBulletin(LoginBulletinRequestDto requestDto, Long userId, String username) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.userId = userId;
        this.username = username;

    }
}
