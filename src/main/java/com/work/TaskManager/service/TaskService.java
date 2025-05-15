package com.work.TaskManager.service;

import com.work.TaskManager.aspect.annotation.ExceptionHandling;
import com.work.TaskManager.aspect.annotation.LogSuccess;
import com.work.TaskManager.aspect.annotation.Logging;
import com.work.TaskManager.aspect.annotation.Tracking;
import com.work.TaskManager.dto.TaskRequestDTO;
import com.work.TaskManager.dto.TaskResponseDTO;
import com.work.TaskManager.model.Task;
import com.work.TaskManager.repository.TaskRepository;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Task convertToEntity(TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setUserId(taskRequestDTO.getUserId());
        return task;
    }

    private TaskResponseDTO convertToDTO(Task entity) {
        TaskResponseDTO taskResponse = new TaskResponseDTO();
        taskResponse.setId(entity.getId());
        taskResponse.setTitle(entity.getTitle());
        taskResponse.setDescription(entity.getDescription());
        taskResponse.setUserId(entity.getUserId());
        return taskResponse;
    }

    @LogSuccess
    public TaskResponseDTO createTask(TaskRequestDTO taskRequest) {
        Task task = convertToEntity(taskRequest);
        taskRepository.save(task);
        return convertToDTO(task);
    }

    @Logging
    @ExceptionHandling
    public TaskResponseDTO getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        return convertToDTO(task);
    }

    @Tracking
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Logging
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
            taskRepository.deleteById(id);
    }

    @Logging
    @ExceptionHandling
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO newTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setTitle(newTask.getTitle());
        task.setDescription(newTask.getDescription());
        task.setUserId(newTask.getUserId());
        taskRepository.save(task);
        return convertToDTO(task);

    }

}
