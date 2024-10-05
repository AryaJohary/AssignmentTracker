package com.aryajohary.classroomassignment.controllers;

import com.aryajohary.classroomassignment.exceptions.CustomEntityNotFoundException;
import com.aryajohary.classroomassignment.repos.AssignmentRepo;
import com.aryajohary.classroomassignment.repos.StudentRepo;
import com.aryajohary.classroomassignment.repos.SubmissionRepo;
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
    public Submission getSyntax(){
        return new Submission();
    }

    @PostMapping
    public Submission createSubmission(@RequestBody Submission submission) {
        assignmentRepo.findById(submission.getAssignmentId())
                .orElseThrow(() -> new CustomEntityNotFoundException("Assignment Id not found"));
        studentRepo.findById(submission.getStudentId())
                .orElseThrow(() -> new CustomEntityNotFoundException("Student Id not found"));

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
