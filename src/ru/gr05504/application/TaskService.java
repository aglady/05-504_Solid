package ru.gr05504.application;

import ru.gr05504.domain.*;
import ru.gr05504.infrastructure.InMemoryTaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // ANEMIC: все проверки здесь, а сама задача ничего не проверяет
    public void createTask(String title, LocalDate deadline, String priority, boolean archived) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Название задачи не может быть пустым");
        if (deadline == null)
            throw new IllegalArgumentException("Дата обязательна");
        Task task = archived ? new ArchivedTask() : new Task();
        task.setTitle(title.trim());
        task.setDeadline(deadline);
        task.setPriority(priority);
        repository.save(task);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // S-1: сервис формирует готовый текст для экрана
    public String buildStatisticsText() {
        List<Task> tasks = repository.findAll();
        int done = 0;
        int overdue = 0;
        // SHOTGUN: правило «просрочено» продублировано в трёх местах (сервис, презентер, окно)
        for (Task t : tasks) {
            if (t.isDone()) done++;
            if (!t.isDone() && t.getDeadline().isBefore(LocalDate.now())) overdue++;
        }
        return "Всего: " + tasks.size() + ", выполнено: " + done + ", просрочено: " + overdue;
    }

    // фильтрация задач
    public List<Task> getFilteredTasks(FilterType filterType){
        List<Task> result = new ArrayList<>();
        TaskFilter filter = filterType.getFilter();
        for (Task task: repository.findAll()){
            if (filter.matches(task))
                result.add(task);
        }
        return result;
    }
}