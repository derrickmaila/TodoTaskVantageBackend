package com.derrick.openvantagetasks.controller;

import com.derrick.openvantagetasks.exception.ResourceNotFoundException;
import com.derrick.openvantagetasks.model.Task;
import com.derrick.openvantagetasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    private TaskRepository taskrepository;

    //Get all Tasks
    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskrepository.findAll();
    }

    //Get a task
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long taskId) throws ResourceNotFoundException {
        Task task =taskrepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));
        return ResponseEntity.ok().body(task);
    }

    //Create a task
    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task){
        return taskrepository.save(task);
    }

    //Update a task
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long taskId,@Valid @RequestBody Task taskinfo) throws  ResourceNotFoundException{
        Task task = taskrepository.findById(taskId)
                .orElseThrow(()-> new ResourceNotFoundException("Task not found for this id :: " + taskId));
        task.setTitle(taskinfo.getTitle());
        task.setCategory(taskinfo.getCategory());
        task.setDescription(taskinfo.getDescription());
        task.setDuedate(taskinfo.getDuedate());
        task.setStatus(taskinfo.getStatus());

        final Task updateTask = taskrepository.save(task);

        return ResponseEntity.ok(updateTask);
    }

    //Delete a task
    @DeleteMapping("/tasks/{id}")
    public Map<String, Boolean> deleteTask(@PathVariable(value = "id") Long taskId)
            throws ResourceNotFoundException {
        Task task = taskrepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));
        taskrepository.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
