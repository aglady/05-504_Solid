package ru.gr05504.domain;

import java.time.LocalDate;

public enum FilterType {
    ALL("Все", task -> true),
    ACTIVE("Активные", task -> !task.isDone()),
    OVERDUE("Просроченные", task -> !task.isDone() && task.getDeadline().isBefore(LocalDate.now())),
    HIGH_PRIORITY("Высокий приоритет", task -> task.getPriority().equals("HIGH"));

    private final String displayName;
    private final TaskFilter filter;

    FilterType(String displayName, TaskFilter filter) {
        this.displayName = displayName;
        this.filter = filter;
    }
    public String getDisplayName() { return displayName; }
    public TaskFilter getFilter() { return filter; }

    public static FilterType fromDisplayName(String name) {
        for (FilterType type : values()) {
            if (type.displayName.equals(name)) {
                return type;
            }
        }
        return ALL; // Значение по умолчанию
    }


}
