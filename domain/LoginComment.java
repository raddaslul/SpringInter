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

    @ManyToOne
    @JoinColumn(name = "bid")
    private LoginBulletin loginBulletin;

    public void save(LoginBulletin loginBulletin) {
        this.loginBulletin = loginBulletin;
    }

    public void update(LoginCommentRequestDto requestDto) {
        this.comments = requestDto.getComments();
    }
}

