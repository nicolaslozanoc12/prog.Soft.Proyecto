package com.software1.controllers;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/user")
    public List<TaskDto> getUserTasks() {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("usuario").list();
        return tasks.stream().map(task -> new TaskDto()).collect(Collectors.toList());
    }

    @PostMapping("/{taskId}/complete")
    public void completeTask(@PathVariable String taskId, @RequestBody Map<String, Object> variables) {
        taskService.complete(taskId, variables);
    }
}
