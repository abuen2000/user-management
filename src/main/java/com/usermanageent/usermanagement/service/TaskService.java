package com.usermanageent.usermanagement.service;

import com.usermanageent.usermanagement.entity.Task;
import com.usermanageent.usermanagement.entity.User;
import com.usermanageent.usermanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Page<Task> getAllUserTask(Long userId, int page, int pageSize) {
        return taskRepository.findAllByPage(userId, PageRequest.of(page, pageSize));
    }

    public void findByDateTimeLessThanOrStatusEqualsIgnoreCase(LocalDateTime dateTime, String status) {
        List<Task> tasks = taskRepository.findByDateTimeLessThanOrStatusEqualsIgnoreCase(dateTime, status);
        for (Task task : tasks) {
            task.setStatus("Done");
            taskRepository.save(task);
        }
    }


}
