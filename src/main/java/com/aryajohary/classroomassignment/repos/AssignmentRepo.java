package com.aryajohary.classroomassignment.repos;

import com.aryajohary.classroomassignment.schemas.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
    @Query("SELECT a FROM Assignment a WHERE a.createdBy.id = :teacherId")
    List<Assignment> listAssignmentsForTeacher(@Param("teacherId") int teacherId);
}
