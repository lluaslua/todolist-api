package io.github.lluaslua.todolist_api.controller;


import io.github.lluaslua.todolist_api.model.Tasks;
import io.github.lluaslua.todolist_api.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    public ResponseEntity<Tasks> addTask(@RequestBody Tasks tasks){
        Tasks savedTask = tasksService.saveTask(tasks);
        URI location = URI.create("/tasks/" + savedTask.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(location)
                .body(tasks);

    }

    @GetMapping
    public ResponseEntity<Tasks> getTaskById(@RequestParam long id){
        return ResponseEntity.ok(tasksService.findById(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTaskById(@RequestParam long id){
        tasksService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Tasks> updateTaskById(@RequestParam Long id,@RequestBody Tasks task){
        tasksService.updateTaskById(id, task);
        return ResponseEntity.ok().build();
    }
}
