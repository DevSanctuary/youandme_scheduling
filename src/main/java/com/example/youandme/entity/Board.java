package com.example.youandme.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //테이블 의미
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //기본 키 생성을 데이터베이스에 위임
    //즉, id 값을 null로 하면 DB가 알아서 AUTO_INCREMENT 해준다.
    private Integer id;

    private String title;
    private String content;
}
