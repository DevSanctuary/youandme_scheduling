package com.project.youandme_schedule.signup.usersignup;

import com.project.youandme_schedule.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSignUpService {

    private final UserSignUpRepository userSignUpRepository;

    @Autowired
    public UserSignUpService(UserSignUpRepository userSignUpRepository) {
        this.userSignUpRepository = userSignUpRepository;
    }

    public boolean isIdExist(Long id) {
        Optional<User> existingUser = userSignUpRepository.findById(id);
        return existingUser.isEmpty();
    }

    public boolean isEmailExist(String email) {
        Optional<User> existingUser = userSignUpRepository.findByEmail(email);
        return existingUser.isEmpty();
    }

    public boolean isPhoneExist(String phone) {
        Optional<User> existingUser = userSignUpRepository.findByPhone(phone);
        return existingUser.isEmpty();
    }

//    public User createUser(User user) {
//        return userSignUpRepository.save(user);
//    }

}
