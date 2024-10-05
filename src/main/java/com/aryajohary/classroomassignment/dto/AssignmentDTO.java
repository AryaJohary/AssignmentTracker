package com.aryajohary.classroomassignment.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssignmentDTO {
    private String title;
    private String description;
    private String dueDate;
    private String className;
    private int createdBy;

}
