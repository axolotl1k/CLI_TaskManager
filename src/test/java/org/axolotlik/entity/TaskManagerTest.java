package org.axolotlik.entity;

import org.axolotlik.exceptions.InvalidInputException;
import org.axolotlik.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = TaskManager.getInstance();
    }

    @AfterEach
    public void tearDown() {
        List<Task> tasksToRemove = taskManager.getTasks().stream()
                .filter(task -> task.getName().equals("test"))
                .toList();
        for (Task task : tasksToRemove) {
            taskManager.removeTask(task);
        }
        taskManager.save();
        taskManager = null;
    }

    @Test
    void testAddTaskIncTaskCount() {
        int currentCount = taskManager.getTaskCount();
        taskManager.addTask(new Task("test", "test", "0001-01-01", "in_progress"));
        int newCount = taskManager.getTaskCount();
        assertEquals(currentCount + 1, newCount);
    }

    @Test
    void testGetTaskAfterAddTask() {
        Task testTask = new Task("test", "test", "0001-01-01", "in_progress");
        taskManager.addTask(testTask);
        assertEquals(testTask, taskManager.getTaskById(testTask.getId()));
    }

    @Test
    void testGetTaskAfterRemoveTaskMustThrowNotFoundException() {
        Task testTask = new Task("test", "test", "0001-01-01", "in_progress");
        taskManager.addTask(testTask);
        taskManager.removeTask(testTask);
        assertThrows(TaskNotFoundException.class, () -> taskManager.getTaskById(testTask.getId()));
    }

    @Test
    void testUpdateTask() {
        Task testTask = new Task("test", "test", "0001-01-01", "in_progress");
        taskManager.addTask(testTask);
        testTask.setStatus("completed");
        assertEquals(testTask, taskManager.getTaskById(testTask.getId()));
    }

    @Test
    void testUpdateTaskDeadlineIfFailed() {
        Task testTask = new Task("test", "test", "0001-01-01", "in_progress");
        taskManager.addTask(testTask);
        taskManager.updateFailed();
        assertEquals(TaskStatus.FAILED, taskManager.getTaskById(testTask.getId()).getStatus());
    }

}