package com.aryajohary.classroomassignment.repos;

import com.aryajohary.classroomassignment.schemas.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    Student findByUsername (String username);
}
