package com.aryajohary.classroomassignment;

import com.aryajohary.classroomassignment.dto.TeacherDTO;
import com.aryajohary.classroomassignment.dto.StudentDTO;
import com.aryajohary.classroomassignment.repos.AssignmentRepo;
import com.aryajohary.classroomassignment.repos.StudentRepo;
import com.aryajohary.classroomassignment.repos.SubmissionRepo;
import com.aryajohary.classroomassignment.repos.TeacherRepo;
import com.aryajohary.classroomassignment.schemas.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final TeacherRepo teacherRepo;
    private final StudentRepo studentRepo;
    private final AssignmentRepo assignmentRepository;
    private final SubmissionRepo submissionRepository;

    @PostConstruct
    public void init() {
        // Sample Teachers using TeacherDTO
        TeacherDTO teacher1DTO = new TeacherDTO();
        teacher1DTO.setUsername("john_doe");
        teacher1DTO.setPassword("password");

        TeacherDTO teacher2DTO = new TeacherDTO();
        teacher2DTO.setUsername("jane_doe");
        teacher2DTO.setPassword("password");

        createTeacher(teacher1DTO);
        createTeacher(teacher2DTO);

        // Sample Students using StudentDTO
        StudentDTO student1DTO = new StudentDTO();
        student1DTO.setUsername("student_one");
        student1DTO.setPassword("password");

        StudentDTO student2DTO = new StudentDTO();
        student2DTO.setUsername("student_two");
        student2DTO.setPassword("password");

        createStudent(student1DTO);
        createStudent(student2DTO);

        // Fetch the created teachers and students
        Teacher teacher1 = teacherRepo.findByUsername("john_doe");
        Teacher teacher2 = teacherRepo.findByUsername("jane_doe");
        Student student1 = studentRepo.findByUsername("student_one");
        Student student2 = studentRepo.findByUsername("student_two");

        // Sample Assignments using Assignment
        Assignment assignment1DTO = new Assignment();
        assignment1DTO.setTitle("Math Assignment 1");
        assignment1DTO.setDescription("Solve the given algebra problems.");
        assignment1DTO.setDueDate(LocalDate.now().plusDays(7).toString());
        assignment1DTO.setClassName("Math 101");

        Assignment assignment2DTO = new Assignment();
        assignment2DTO.setTitle("Physics Assignment 1");
        assignment2DTO.setDescription("Explain the laws of motion.");
        assignment2DTO.setDueDate(LocalDate.now().plusDays(5).toString());
        assignment2DTO.setClassName("Physics 201");

        Assignment assignment1 = createAssignment(assignment1DTO, teacher1);
        Assignment assignment2 = createAssignment(assignment2DTO, teacher2);

        // Sample Submissions using Submission
        Submission submission1DTO = new Submission();
        submission1DTO.setAssignment(assignment1);
        submission1DTO.setStudent(student1);
        submission1DTO.setSubmissionText("Here is my solution.");
        submission1DTO.setSubmissionDate(LocalDate.now().toString());
        submission1DTO.setAttachmentUrl("http://example.com/submission1");

        Submission submission2DTO = new Submission();
        submission2DTO.setAssignment(assignment2);
        submission2DTO.setStudent(student2);
        submission2DTO.setSubmissionText("Here is my explanation.");
        submission2DTO.setSubmissionDate(LocalDate.now().toString());
        submission2DTO.setAttachmentUrl("http://example.com/submission2");

        createSubmission(submission1DTO);
        createSubmission(submission2DTO);
    }

    // Methods to create Teacher, Student, Assignment, Submission

    private Teacher createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setUsername(teacherDTO.getUsername());
        teacher.setPassword(teacherDTO.getPassword());
        teacher.setRole(Role.Teacher);
        return teacherRepo.save(teacher);
    }

    private Student createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setUsername(studentDTO.getUsername());
        student.setPassword(studentDTO.getPassword());
        student.setRole(Role.Student);
        return studentRepo.save(student);
    }

    private Assignment createAssignment(Assignment assignmentDTO, Teacher createdBy) {
        Assignment assignment = new Assignment();
        assignment.setTitle(assignmentDTO.getTitle());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setDueDate(assignmentDTO.getDueDate());
        assignment.setClassName(assignmentDTO.getClassName());
        assignment.setCreatedBy(createdBy);
        return assignmentRepository.save(assignment);
    }

    private Submission createSubmission(Submission submissionDTO) {
        Submission submission = new Submission();
        submission.setAssignment(submissionDTO.getAssignment());
        submission.setStudent(submissionDTO.getStudent());
        submission.setSubmissionText(submissionDTO.getSubmissionText());
        submission.setSubmissionDate(submissionDTO.getSubmissionDate());
        submission.setAttachmentUrl(submissionDTO.getAttachmentUrl());
        return submissionRepository.save(submission);
    }
}
