package ru.gr05504.application;

import ru.gr05504.domain.Task;
import ru.gr05504.domain.TaskFilter;

public class AllTaskFilter implements TaskFilter {
    @Override
    public boolean matches(Task task) {
        return true;
    }
}
