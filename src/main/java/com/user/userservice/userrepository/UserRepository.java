package com.user.userservice.userrepository;

import com.user.userservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findUserById(int id);

    User save(User user);

    @Override
    List<User> findAll();
}
