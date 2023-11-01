package com.project.youandme_schedule.login.userlogin;

import com.project.youandme_schedule.securityconfig.PasswordEncoderUtil;
import com.project.youandme_schedule.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLogInService {

    private final UserLogInRepository userSignInRepository;

    @Autowired
    public UserLogInService(UserLogInRepository userSignInRepository) {
        this.userSignInRepository = userSignInRepository;
    }

    public Optional<User> authenticatedUser(Long id, String password) {
        Optional<User> existingUser = userSignInRepository.findById(id);
        if (existingUser.isPresent() && PasswordEncoderUtil.matches(password, existingUser.get().getPassword())) {
            return existingUser;
        }
        return Optional.empty();
    }

}
