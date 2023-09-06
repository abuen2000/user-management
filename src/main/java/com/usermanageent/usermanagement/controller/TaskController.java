package com.usermanageent.usermanagement.controller;

import com.usermanageent.usermanagement.dto.TaskDTO;
import com.usermanageent.usermanagement.dto.UserDTO;
import com.usermanageent.usermanagement.entity.Task;
import com.usermanageent.usermanagement.entity.User;
import com.usermanageent.usermanagement.exception.ApplicationException;
import com.usermanageent.usermanagement.exception.NotFoundException;
import com.usermanageent.usermanagement.mapper.TaskMapper;
import com.usermanageent.usermanagement.mapper.UserMapper;
import com.usermanageent.usermanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class TaskController {
    private final TaskMapper taskMapper;
    private final TaskService taskService;

    @PostMapping("/{userId}/tasks")
    public ResponseEntity<TaskDTO> createUser(@RequestBody TaskDTO taskDTO) {
        return Optional.ofNullable(taskDTO)
                .map(taskMapper::asTask)
                .map(taskService::save)
                .map(taskMapper::asTaskDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(ApplicationException::new);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId,
                                              @RequestBody TaskDTO taskDTO) {

        Optional<Task> optionalTask = taskService.getTaskById(taskId);

        return optionalTask.map(existingTask -> {
            existingTask.setName(taskDTO.getName());
            return Optional.ofNullable(existingTask)
                    .map(taskService::save)
                    .map(taskMapper::asTaskDTO)
                    .map(ResponseEntity::ok)
                    .orElseThrow(ApplicationException::new);
        }).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        Optional<Task> optionalTask = taskService.getTaskById(taskId);
        return optionalTask.map(existingTask -> {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok("Deleted");
        }).orElseThrow(NotFoundException::new);
    }

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<Page<TaskDTO>> getAllUsers(@PathVariable Long userId,
                                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Page<TaskDTO> taskDTOS = taskService.getAllUserTask(userId, page, pageSize)
                .map(taskMapper::asTaskDTO);
        return ResponseEntity.ok(taskDTOS);
    }
}

