package com.project.youandme_schedule.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String password;
    private String name;
    private int age;
    private String sex;
    private String email;
    private String phone;
    private String nickname;
    private String verificationCode;

}
