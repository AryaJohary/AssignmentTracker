package com.aryajohary.classroomassignment.controllers;

import com.aryajohary.classroomassignment.dto.AssignmentDTO;
import com.aryajohary.classroomassignment.dto.SubmissionDTO;
import com.aryajohary.classroomassignment.exceptions.CustomEntityNotFoundException;
import com.aryajohary.classroomassignment.repos.AssignmentRepo;
import com.aryajohary.classroomassignment.repos.StudentRepo;
import com.aryajohary.classroomassignment.repos.SubmissionRepo;
import com.aryajohary.classroomassignment.schemas.Assignment;
import com.aryajohary.classroomassignment.schemas.Student;
import com.aryajohary.classroomassignment.schemas.Submission;
import com.aryajohary.classroomassignment.schemas.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submisssions")
public class SubmissionController {

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SubmissionRepo submissionRepo;

    @GetMapping("/syntax")
    public SubmissionDTO getSyntax(){
        return new SubmissionDTO();
    }

    @PostMapping
    public Submission createSubmission(@RequestBody SubmissionDTO submissionDTO) {
        Student student = studentRepo.findById(submissionDTO.getStudentId())
                .orElseThrow(() -> new CustomEntityNotFoundException("Student not found"));

        Assignment assignment = assignmentRepo.findById(submissionDTO.getAssignmentId())
                .orElseThrow(() -> new CustomEntityNotFoundException("Assignment Id not found"));

        Submission submission = new Submission();
        submission.setAssignment(assignment);
        submission.setStudent(student);
        submission.setAttachmentUrl(submission.getAttachmentUrl());
        submission.setSubmissionDate(submissionDTO.getSubmissionDate());
        submission.setSubmissionText(submissionDTO.getSubmissionText());
        return submissionRepo.save(submission);
    }

    @GetMapping
    public List<Submission> findAll(){
        return submissionRepo.findAll();
    }

    @GetMapping("/{id}")
    public Submission getSubmission(@PathVariable int id){
        Submission submission = submissionRepo.findById(id).orElse(null);
        if(submission==null){
            throw new CustomEntityNotFoundException("Submission not found");
        }
        return submission;
    }

}
