package com.aryajohary.classroomassignment.repos;

import com.aryajohary.classroomassignment.schemas.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername (String username);
}
