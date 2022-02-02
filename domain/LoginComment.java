package com.sparta.springinter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.springinter.dto.LoginCommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class LoginComment extends  Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long cid;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private Long bid;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "bulletin", nullable = false)
    private LoginBulletin loginBulletin;

    public LoginComment (LoginCommentRequestDto requestDto, LoginBulletin loginBulletin, Long bid, Long userId, String username){
        this.comments = requestDto.getComments();
        this.loginBulletin = loginBulletin;
        this.bid = bid;
        this.userId = userId;
        this.username = username;
    }

    public void save(LoginBulletin loginBulletin) {
        this.loginBulletin = loginBulletin;
    }

    public void update(LoginCommentRequestDto requestDto) {
        this.comments = requestDto.getComments();
    }
}

