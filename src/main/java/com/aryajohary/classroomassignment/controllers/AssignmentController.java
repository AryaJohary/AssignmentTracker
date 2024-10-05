package com.aryajohary.classroomassignment.controllers;

import com.aryajohary.classroomassignment.dto.AssignmentDTO;
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
    public AssignmentDTO getSyntax(){
        return new AssignmentDTO();
    }

    @PostMapping
    public Assignment createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        Teacher teacher = teacherRepo.findById(assignmentDTO.getCreatedBy())
                .orElseThrow(() ->
                        new CustomEntityNotFoundException("Teacher not found with this Id"));

        Assignment assignment = new Assignment();
        assignment.setCreatedBy(teacher);
        assignment.setClassName(assignmentDTO.getClassName());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setDueDate(assignmentDTO.getDueDate());
        assignment.setTitle(assignmentDTO.getTitle());
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
