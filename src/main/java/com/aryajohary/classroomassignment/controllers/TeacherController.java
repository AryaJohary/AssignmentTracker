package com.aryajohary.classroomassignment.controllers;

import com.aryajohary.classroomassignment.dto.TeacherDTO;
import com.aryajohary.classroomassignment.exceptions.CustomEntityNotFoundException;
import com.aryajohary.classroomassignment.repos.AssignmentRepo;
import com.aryajohary.classroomassignment.repos.SubmissionRepo;
import com.aryajohary.classroomassignment.repos.TeacherRepo;
import com.aryajohary.classroomassignment.schemas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Autowired
    private SubmissionRepo submissionRepo;

    @GetMapping("/syntax")
    public TeacherDTO getSyntax(){
        return new TeacherDTO();
    }

    @GetMapping
    public List<Teacher> findAll(){
        return teacherRepo.findAll();
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody TeacherDTO teacherDTO){

        Teacher teacher = new Teacher();
        teacher.setUsername(teacherDTO.getUsername());
        teacher.setPassword(teacherDTO.getPassword());
        teacher.setRole(Role.Teacher);
        return teacherRepo.save(teacher);

    }

    @GetMapping("/{id}/listAssignments")
    public List<Assignment> listAssignment(@PathVariable int id){
        return assignmentRepo.listAssignmentsForTeacher(id);
    }

    @GetMapping("/{id}/listSubmissions")
    public List<Submission> listSubmission(@PathVariable int id){
        return submissionRepo.listSubmissionsForTeacher(id);
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable int id){
        Teacher teacher = teacherRepo.findById(id).orElse(null);
        if(teacher==null){
            throw new CustomEntityNotFoundException("Teacher not found");
        }
        return teacher;
    }

    @GetMapping("/showAll")
    public String findAllTeachers(Model theModel){
        theModel.addAttribute("users", teacherRepo.findAll());
        return "usersList";
    }

}
