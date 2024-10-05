package com.aryajohary.classroomassignment.controllers;

import com.aryajohary.classroomassignment.exceptions.CustomEntityNotFoundException;
import com.aryajohary.classroomassignment.repos.AssignmentRepo;
import com.aryajohary.classroomassignment.repos.TeacherRepo;
import com.aryajohary.classroomassignment.schemas.Assignment;
import com.aryajohary.classroomassignment.schemas.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private AssignmentRepo assignmentRepo;

    @GetMapping("/syntax")
    public Assignment getSyntax(){
        return new Assignment();
    }

    @PostMapping
    public Assignment createAssignment(@RequestBody Assignment assignment) {
        Teacher teacher = teacherRepo.findById(assignment.getCreatedBy().getId())
                .orElseThrow(() ->
                        new CustomEntityNotFoundException("Teacher not found with this Id"));

        assignment.setCreatedBy(teacher);

        return assignmentRepo.save(assignment);
    }

    @GetMapping
    public List<Assignment> findAll(){
        return assignmentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Assignment getAssignment(@PathVariable int id){
        Assignment assignment = assignmentRepo.findById(id).orElse(null);
        if(assignment==null){
            throw new CustomEntityNotFoundException("Assignment not found");
        }
        return assignment;
    }

}
