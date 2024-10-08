package com.aryajohary.classroomassignment.schemas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    private Assignment assignment;

    @NotNull
    @ManyToOne
    private Student student;

    @NotNull
    private String submissionText;

    @NotNull
    private String attachmentUrl;

    @NotNull
    private String submissionDate;

}
