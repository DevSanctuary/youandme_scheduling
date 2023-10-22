package com.project.youandme_schedule.friend;

import com.project.youandme_schedule.dto.FriendDto;
import com.project.youandme_schedule.trainer.Trainer;
import com.project.youandme_schedule.trainer.TrainerRepository;
import com.project.youandme_schedule.user.User;
import com.project.youandme_schedule.user.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    public String addFriend(FriendDto dto) {
        Optional<User> user = userRepository.findByNickname(dto.getUserNickname());
        Optional<Trainer> trainer = trainerRepository.findByNickname(dto.getTrainerNickname());

        if (!user.isPresent() || !trainer.isPresent()) {
            throw new RuntimeException("회원이나 강사를 찾을 수 없습니다.");
        }

        Optional<Friend> existingFriend = friendRepository.findByUserNicknameAndTrainerNickname(user.get().getNickname(), trainer.get().getNickname());
        if (existingFriend.isPresent()) {
            throw new RuntimeException("이미 친구가 존재합니다.");
        }

        Friend friend = new Friend();
        friend.setUserNickname(user.get().getNickname());
        friend.setTrainerNickname(trainer.get().getNickname());

        friendRepository.save(friend);

        return "친구추가가 완료되었습니다.";
    }
}
