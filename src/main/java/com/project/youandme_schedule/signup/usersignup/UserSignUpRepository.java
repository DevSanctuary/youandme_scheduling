package com.project.youandme_schedule.signup.usersignup;

import com.project.youandme_schedule.user.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSignUpRepository extends JpaRepository<User, Long> {
    @NonNull
    Optional<User> findById(Long id);
    @NonNull
    Optional<User> findByEmail(String email);
    @NonNull
    Optional<User> findByPhone(String phone);

}
