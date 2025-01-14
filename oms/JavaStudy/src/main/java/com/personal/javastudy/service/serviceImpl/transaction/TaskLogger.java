package com.personal.javastudy.service.serviceImpl.transaction;

import com.personal.javastudy.models.transactions.Task;
import com.personal.javastudy.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TaskLogger {

    private final TaskRepository taskRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logTaskAction(Long taskId, String action) {
        Task logEntry = new Task();
        logEntry.setName("Audit Log");
        logEntry.setDescription("Action: " + action + ", Task ID: " + taskId);
        logEntry.setStatus("LOGGED");
        taskRepository.save(logEntry);
    }
}
