package com.project.youandme_schedule.friend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_nickname", nullable = false)
    private String userNickname;
    @Column(name = "trainer_nickname", nullable = false)
    private String trainerNickname;

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getTrainerNickname() {
        return trainerNickname;
    }

    public void setTrainerNickname(String trainerNickname) {
        this.trainerNickname = trainerNickname;
    }

}
