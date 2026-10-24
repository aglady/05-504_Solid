package ru.gr05504.ui;

import ru.gr05504.domain.Task;

import java.util.List;

public interface TaskView {
    String getTaskTitle();
    String getDeadlineText();
    String getPriority();
    boolean isArchived();
    String getFilter();
    Task getSelectedTask();

    void clearForm();
    void showTasks(List<Task> tasks);
    void showStatistics(String text);
    void showError(String message);
}