import ru.gr05504.application.TaskService;
import ru.gr05504.ui.TaskWindow;

void main() {
    TaskService service = new TaskService();
    new TaskWindow(service).setVisible(true);
}
