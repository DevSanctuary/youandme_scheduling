package com.project.youandme_schedule.login.userlogin;

import com.project.youandme_schedule.user.User;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLogInRepository extends JpaRepository<User, Long> {
    @NotNull
    Optional<User> findById(Long id);
}
