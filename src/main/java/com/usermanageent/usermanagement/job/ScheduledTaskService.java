package com.usermanageent.usermanagement.job;

import com.usermanageent.usermanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ScheduledTaskService {
    private final TaskService taskService;

    @Scheduled(cron = "0 0 * * * *")
    public void updateTaskStatusToPendingJob() {
        taskService.findByDateTimeLessThanOrStatusEqualsIgnoreCase(LocalDateTime.now(), "Pending");
    }
}

