package com.aryajohary.classroomassignment.repos;

import com.aryajohary.classroomassignment.schemas.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
    Teacher findByUsername (String username);
}
