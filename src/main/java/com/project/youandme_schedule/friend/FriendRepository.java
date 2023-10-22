package com.project.youandme_schedule.friend;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    Optional<Friend> findByUserNicknameAndTrainerNickname(String userNickname, String trainerNickname);
}
