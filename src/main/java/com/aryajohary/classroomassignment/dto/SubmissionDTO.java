package com.aryajohary.classroomassignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubmissionDTO {
    private int assignmentId;
    private int studentId;
    private String submissionText;
    private String attachmentUrl;
    private String submissionDate;
}
