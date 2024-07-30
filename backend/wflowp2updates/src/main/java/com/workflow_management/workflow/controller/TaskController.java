package com.workflow_management.workflow.controller;

import com.workflow_management.workflow.model.Task;
import com.workflow_management.workflow.service.TaskService;  // Ensure this line is present
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Task Controller handles the API to create the task or the activity of the user
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @GetMapping("/list")
    public List<Task> listTasks() {
        return taskService.findAllTasks();
    }
}