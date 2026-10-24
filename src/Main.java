import ru.gr05504.application.TaskService;
import ru.gr05504.domain.Task;
import ru.gr05504.domain.TaskRepository;
import ru.gr05504.infrastructure.InMemoryTaskRepository;
import ru.gr05504.ui.TaskWindow;

void main() {
    TaskRepository repository = new InMemoryTaskRepository();
    TaskService service = new TaskService(repository);
    new TaskWindow(service).setVisible(true);
}
