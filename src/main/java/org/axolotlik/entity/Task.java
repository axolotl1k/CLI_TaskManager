package org.axolotlik.entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private TaskStatus status;

    public Task(int id, String name, String description, String deadline, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = dateParser(deadline);
        this.status = TaskStatus.fromString(status);
    }

    public Task(String name, String description, String deadline, String status) {
        this.id = TaskManager.getInstance().nextTaskCount();
        this.name = name;
        this.description = description;
        this.deadline = dateParser(deadline);
        this.status = TaskStatus.fromString(status);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = dateParser(deadline);
        if (this.deadline.isBefore(LocalDate.now())) {
            setStatus("failed");
        } else if (this.deadline.isAfter(LocalDate.now())) {
            setStatus("in_progress");
        }
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = TaskStatus.fromString(status);
    }

    @Override
    public String toString() {
        return "Task №" + id +
                "\n\tname = " + name +
                "\n\tdeadline = " + deadline +
                "\n\tstatus = " + status +
                "\n\tdescription = " + description + "\n";
    }

    private LocalDate dateParser(String deadline) {
        List<Integer> deadlineInt = Arrays.stream(deadline.split("-"))
                .map(Integer::parseInt).toList();
        return LocalDate.of(deadlineInt.get(0), deadlineInt.get(1), deadlineInt.get(2));
    }
}
