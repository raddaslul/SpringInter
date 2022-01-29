package com.sparta.springinter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.springinter.dto.LoginBulletinRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class LoginBulletin extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @OrderBy("id desc")
    @JsonIgnoreProperties({"loginBulletin"})
    @OneToMany(mappedBy = "loginBulletin", fetch = FetchType.EAGER)
    private List<LoginComment> loginComments;



    public LoginBulletin(LoginBulletinRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
