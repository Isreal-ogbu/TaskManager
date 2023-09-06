package com.example.TaskManager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private LocalDate dueDate;

    @NonNull
    private LocalDate expectedCompletionDate;

    private Choices status;

}
