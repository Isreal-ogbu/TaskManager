package com.example.TaskManager.controller;

import com.example.TaskManager.exception.GlobalExceptions;
import com.example.TaskManager.model.Choices;
import com.example.TaskManager.model.Task;
import com.example.TaskManager.repository.TaskRepository;
import com.example.TaskManager.service.ErrorService;
import com.example.TaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/")
public class TaskController {

    @GetMapping("/task")
    public ResponseEntity<List<Task>> getAllTask(){
        return TaskService.getTask();
    }

    @PostMapping("/task")
    public ResponseEntity<Task> postTask(@Validated @RequestBody Task task){
        return TaskService.createTask(task);
    }

    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable int id){
        return TaskService.getATask(id);
    }

    @GetMapping("/task-title/{title}")
    public ResponseEntity<List<Task>> getAllTaskByTitle(@PathVariable String title){
        return TaskService.getAllTaskByTitle(title);
    }

    @GetMapping("/task-status/{status}")
    public ResponseEntity<List<Task>> getAllTaskByStatus(@PathVariable Choices status){
        return TaskService.getAllTaskByStatus(status);
    }

    @GetMapping("/task-date/{date}")
    public ResponseEntity<List<Task>> getAllTaskByDate(@PathVariable LocalDate date){
        return TaskService.getAllTaskByDate(date);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<String> updateTask(@PathVariable int id, @RequestBody Task task){
        return TaskService.updateAllDetaildTask(id, task);
    }

    @PutMapping("/change-status/{id}")
    public ResponseEntity<Task> updateStatus(@PathVariable int id,@RequestBody Task status){
        return TaskService.updateStatus(id, status);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id){
        return TaskService.deleteTask(id);
    }

    @GetMapping(path = "/error")
    @ResponseBody
    public ResponseEntity<String> error(){
        return ErrorService.errorHandler();
    }

}
