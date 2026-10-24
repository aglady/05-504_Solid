package ru.gr05504.application;

import ru.gr05504.domain.Task;
import ru.gr05504.domain.TaskFilter;

public class HighPriorityFilter implements TaskFilter {
    @Override
    public boolean matches(Task task) {
        // PRIMITIVE: сравнение строки с "HIGH"
        return task.getPriority().equals("HIGH");
    }
}
