package com.aryajohary.classroomassignment.repos;

import com.aryajohary.classroomassignment.schemas.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubmissionRepo extends JpaRepository<Submission, Integer> {
    @Query("select s from Submission s join Assignment a on s.assignmentId = a.id where a.createdBy.id = :teacherId")
    List<Submission> listSubmissionsForTeacher(@Param("teacherId") int teacherId);

    @Query("SELECT s from Submission s WHERE s.studentId = :studentId")
    List<Submission> listAssignmentsDoneByStudent(@Param("studentId") int studentId);

}
