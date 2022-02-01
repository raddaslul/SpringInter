package com.sparta.springinter.domain;


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
    private Long id;

    @Column(nullable = false)
    private String comments;

//    @Column(nullable = false)
//    private String username;
//
//    @Column(nullable = false)
//    private Long userId;

    @ManyToOne
    @JoinColumn(name = "bid")
    private LoginBulletin loginBulletin;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public LoginComment (LoginCommentRequestDto requestDto, LoginBulletin loginBulletin, User user){
        this.comments = requestDto.getComments();
        this.user = user;
        this.loginBulletin = loginBulletin;
    }

    public void save(LoginBulletin loginBulletin) {
        this.loginBulletin = loginBulletin;
    }

    public void update(LoginCommentRequestDto requestDto) {
        this.comments = requestDto.getComments();
    }
}

