package ru.gr05504.ui;

import ru.gr05504.application.*;
import ru.gr05504.domain.FilterType;
import ru.gr05504.domain.Task;
import ru.gr05504.domain.TaskFilter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

// GOD OBJECT: форма, разбор дат, фильтры, выполнение задач и статистика в одном классе
public class TaskPresenter {
    private final TaskView view;

    private final TaskService service;

    public TaskPresenter(TaskView view, TaskService service) {
        this.view = view;
        this.service = service;
    }

    public void start() {
        refresh("Все");
    }

    public void saveClicked() {
        try {
            LocalDate deadline = parseDeadline(view.getDeadlineText());
            service.createTask(view.getTaskTitle(), deadline, view.getPriority(), view.isArchived());
            view.clearForm();
            refresh(view.getFilter());
        } catch (IllegalArgumentException ex) {
            view.showError(ex.getMessage());
        }
    }

    public void completeSelected() {
        Task task = view.getSelectedTask();
        if (task == null) {
            view.showError("Выберите задачу в списке");
            return;
        }
        task.setDone(true);   // ANEMIC: правило выполнения живёт снаружи задачи
        refresh(view.getFilter());
    }

    public void filterChanged() {
        refresh(view.getFilter());
    }

    private void refresh(String filterName) {
        FilterType filterType = FilterType.fromDisplayName(filterName);

        List<Task> result = service.getFilteredTasks(filterType);

        view.showTasks(result);
        view.showStatistics(service.buildStatisticsText());
    }

    private LocalDate parseDeadline(String text) {
        if (text == null || text.isBlank())
            throw new IllegalArgumentException("Дата выполнения не может быть пустой");
        try {
            return LocalDate.parse(text.trim());
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Дата должна быть в формате yyyy-MM-dd");
        }
    }
}