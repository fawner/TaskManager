package com.work.TaskManager.controller;

import com.work.TaskManager.dto.TaskRequestDTO;
import com.work.TaskManager.dto.TaskResponseDTO;
import com.work.TaskManager.model.Task;
import com.work.TaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO taskRequest){
        return taskService.createTask(taskRequest);
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }


    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO newTask) {
        return taskService.updateTask(id, newTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

}
