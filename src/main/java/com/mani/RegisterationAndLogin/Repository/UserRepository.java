package com.mani.RegisterationAndLogin.Repository;

import com.mani.RegisterationAndLogin.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    @Query("SELECT COUNT(u) FROM User u WHERE u.email = :email")
    long getEmailCount(String email);

}
