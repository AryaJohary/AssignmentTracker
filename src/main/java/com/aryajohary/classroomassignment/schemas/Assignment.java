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
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private String dueDate;

    @NotNull
    private String className;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

}
