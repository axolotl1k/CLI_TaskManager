package org.axolotlik.entity;

import org.axolotlik.exceptions.TaskNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static TaskManager instance;
    private List<Task> tasks;
    private int taskCount;

    private TaskManager() {
        try {
            this.tasks = FileManager.load();
            this.taskCount = this.tasks.stream().mapToInt(Task::getId).max().orElse(0);
            updateFailed();
        } catch (FileNotFoundException e){
            this.tasks = new ArrayList<>();
            taskCount = 0;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public int nextTaskCount() {
        taskCount++;
        return taskCount;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public Task getTaskById(int id){
        return this.tasks.stream().filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void save(){
        try {
            FileManager.save(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage()
                    + "\nTasks not saved.");
        }
    }

    private void updateFailed(){
        tasks.stream()
                .filter(task -> task.getDeadline().isBefore(LocalDate.now()))
                .forEach(task -> task.setStatus("failed"));
    }
}
