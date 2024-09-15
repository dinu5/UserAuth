package com.dino.userauthentication.repositories;

import com.dino.userauthentication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepo extends JpaRepository<User,Long> {
    public Optional<User> findUserByEmail(String email);
}
