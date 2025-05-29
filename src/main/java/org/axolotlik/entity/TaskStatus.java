package org.axolotlik.entity;

public enum TaskStatus {
    IN_PROGRESS("In_progress"),
    COMPLETED("Completed"),
    FAILED("Failed");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public static TaskStatus fromString(String status) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.status.equalsIgnoreCase(status)) {
                return taskStatus;
            }
        }
        throw new IllegalArgumentException("Invalid task status: " + status);
    }


    @Override
    public String toString() {
        return status;
    }
}
