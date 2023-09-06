package com.example.TaskManager.repository;

import com.example.TaskManager.model.Choices;
import com.example.TaskManager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<List<Task>> findBytitle(String title);
    Optional<List<Task>> findByDueDate(LocalDate date);
    Optional<List<Task>> findByStatus(Choices choices);
}
