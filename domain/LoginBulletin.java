package com.sparta.springinter.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.springinter.dto.LoginBulletinRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginBulletin extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "bid")
    private Long bid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @OrderBy("cid desc")
    @JsonIgnoreProperties({"loginBulletin"})
    @OneToMany(mappedBy = "loginBulletin", cascade = CascadeType.REMOVE)
    private List<LoginComment> loginComments;

    public LoginBulletin(LoginBulletinRequestDto requestDto, User user, Long userId) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.userId = userId;
        this.user = user;
    }
}