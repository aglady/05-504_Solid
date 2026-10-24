package ru.gr05504.domain;

public interface TaskFilter {
    boolean matches(Task task);
}
