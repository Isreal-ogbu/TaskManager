package com.example.TaskManager.service;

import com.example.TaskManager.logging.TaskMontoring;
import com.example.TaskManager.model.Choices;
import com.example.TaskManager.model.Task;
import com.example.TaskManager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Configuration
@Service
@Transactional
public class TaskService {

    private static TaskRepository taskRepository = null;

    public static TaskRepository getTaskRepository() {
        return taskRepository;
    }

    @Autowired
    public TaskService(TaskRepository taskRepository){
        TaskService.taskRepository = taskRepository;
    }
    public static ResponseEntity<List<Task>> getTask(){
        List<Task> tasks = getTaskRepository().findAll();
        return ResponseEntity.ok(tasks);
    }

    public static ResponseEntity<Task> createTask(Task task){
        try{
            getTaskRepository().save(task);
            String text = String.format("Created a task with Named: %s\n", task.getTitle());
            TaskMontoring.monitor(text);
            return ResponseEntity.ok(task);
        } catch (Exception e){
            String text = String.format("An error occured while creating a task with Named: %s with error message: %s", task.getTitle(), e.getMessage());
            TaskMontoring.monitor(text);
        }
        return ResponseEntity.ok(null);
    }

    public static Task getATask(int id){
        return ResponseEntity.ok(getTaskRepository().findById(id).orElseThrow()).getBody();
    }

    public static ResponseEntity<List<Task>> getAllTaskByTitle(String title){
        return ResponseEntity.ok(getTaskRepository().findBytitle(title).orElseThrow());
    }

    public static ResponseEntity<List<Task>> getAllTaskByStatus(Choices status){
        return ResponseEntity.ok(getTaskRepository().findByStatus(status).orElseThrow());
    }

    public static ResponseEntity<List<Task>> getAllTaskByDate(LocalDate date){
        List<Task> tasks = getTaskRepository().findByDueDate(date).orElseThrow();
        List<Task> filterTask = tasks.stream().filter(e-> e.getDueDate().equals(LocalDate.now())).toList();
        return ResponseEntity.ok(filterTask);
    }

    public static ResponseEntity<String> updateAllDetaildTask(int id, Task task){
        Task instance = getATask(id);
        instance.setDescription(task.getDescription());
        instance.setTitle(task.getTitle());
        instance.setStatus(task.getStatus());
        instance.setDueDate(task.getDueDate());
        getTaskRepository().save(instance);
        return ResponseEntity.ok("Updated details");
    }

    public static ResponseEntity<Task> updateStatus(int id, Task status){
        Task task = getATask(id);
        if(status.getStatus() != null){
            task.setStatus(status.getStatus());
        }
        getTaskRepository().save(task);
        return ResponseEntity.ok(task);
    }

    public static ResponseEntity<String> deleteTask(int id){
        getTaskRepository().deleteById(id);
        return ResponseEntity.ok("Deleted Task Successfully");
    }
}
