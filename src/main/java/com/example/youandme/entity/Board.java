package com.example.youandme.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "board") // "board" 테이블과 매핑
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bno")
    private Integer bno;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(name = "reg_Date")
    private LocalDateTime regDate;

    @Column(name = "update_Date")
    private LocalDateTime updateDate;

    @Column(name = "filename")
    private String filename;

    @Column(name = "filepath")
    private String filepath;

}
