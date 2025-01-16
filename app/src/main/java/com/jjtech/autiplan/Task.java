package com.jjtech.autiplan;

public class Task {
    private String taskName;
    private String taskDetails;
    private long taskTimeInMillis;

    public Task(String taskName, String taskDetails, long taskTimeInMillis) {
        this.taskName = taskName;
        this.taskDetails = taskDetails;
        this.taskTimeInMillis = taskTimeInMillis;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public long getTaskTimeInMillis() {
        return taskTimeInMillis;
    }
}
