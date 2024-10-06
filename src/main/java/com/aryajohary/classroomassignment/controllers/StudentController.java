package com.aryajohary.classroomassignment.controllers;

import com.aryajohary.classroomassignment.dto.StudentDTO;
import com.aryajohary.classroomassignment.exceptions.CustomEntityNotFoundException;
import com.aryajohary.classroomassignment.repos.StudentRepo;
import com.aryajohary.classroomassignment.repos.SubmissionRepo;
import com.aryajohary.classroomassignment.schemas.Role;
import com.aryajohary.classroomassignment.schemas.Student;
import com.aryajohary.classroomassignment.schemas.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private SubmissionRepo submissionRepo;

    @GetMapping("/syntax")
    public StudentDTO getSyntax(){
        return new StudentDTO();
    }

    @GetMapping("/showAll")
    public String findAllStudents(Model theModel){
        theModel.addAttribute("users", studentRepo.findAll());
        return "usersList";
    }

    @GetMapping
    public List<Student> findAll(){
        return studentRepo.findAll();
    }

    @PostMapping
    public Student createStudent(@RequestBody StudentDTO studentDTO){

        Student student = new Student();
        student.setUsername(studentDTO.getUsername());
        student.setPassword(studentDTO.getPassword());
        student.setRole(Role.Student);
        return studentRepo.save(student);

    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id){
        Student student = studentRepo.findById(id).orElse(null);
        if(student==null){
            throw new CustomEntityNotFoundException("Student not found");
        }
        return student;
    }

    @GetMapping("/{id}/listSubmissions")
    public List<Submission> listSubmission(@PathVariable int id){
        return submissionRepo.listAssignmentsDoneByStudent(id);
    }

//    @GetMapping("/{id}/listSubmissions")
//    public String listSubmission(@PathVariable int id, Model theModel){
//        theModel.addAttribute("tasks", submissionRepo.listAssignmentsDoneByStudent(id));
//        return "tasks";
//    }

}
