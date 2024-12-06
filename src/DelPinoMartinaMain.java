import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class DelPinoMartinaMain {

    Scanner input = new Scanner(System.in);
    private ArrayList<DelPinoMartinaEvent> events = new ArrayList<>();

    public static void main(String[] args) {
        DelPinoMartinaMain program = new DelPinoMartinaMain();
        program.inicio();
    }

    public void inicio() {

        int opcion ;
        boolean end = false;

        System.out.println("Bienvenido a Event Planner: " );

        while (true) {

            System.out.println(" Seleccione una opción: ");
            System.out.println("1 = Añadir evento");
            System.out.println("2 =  Borrar evento");
            System.out.println("3 =   Listar eventos");
            System.out.println("4 = Marcar/desmarcar tarea de un evento como completada");
            System.out.println("5 = Salir");

            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1: {
                    System.out.println("Creado evento" );
                    addEvent();
                    break;
                }
                case 2: {
                    System.out.println("Borrar");
                    deleteEvent();
                    break;
                }
                case 3: {
                    System.out.println("Listar");
                    eventsList();
                    break;
                }
                case 4: {
                    System.out.println("Marcar/Desmarcar");
                    idDone();
                    break;
                }
                case 5: {
                    System.out.println("saliendo...");
                    end = true;
                    return;
                }
                default: {
                    System.out.println("Opción no válida. Por favor, elige 1, 2, 3, 4 o 5");
                    break;
                }
            }
        }
    }

    public LocalDate validateDate() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            try {
                System.out.print("Introduce la fecha del evento (YYYY-MM-DD): ");
                String userInput = input.nextLine();
                return LocalDate.parse(userInput, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Intenta nuevamente.");
            }
        }
    }

    public void addEvent() {

        System.out.println("Introduce nombre del evento: " );
        String name = input.nextLine().trim();
        LocalDate date = validateDate();
        System.out.print("Introduce la prioridad del evento (HIGH, MEDIUM, LOW): ");
        DelPinoMartinaEvent.Priority priorityInput = DelPinoMartinaEvent.Priority.valueOf(input.nextLine().toUpperCase());

        DelPinoMartinaEvent newEvent = new DelPinoMartinaEvent(name, date, priorityInput);

        System.out.print("¿Deseas agregar tareas al evento? (s/n): ");
        String response = input.nextLine().toLowerCase();
        if (response.equals("s")) {
            System.out.print("Introduce la descripción de la tarea: ");
            String taskText = input.nextLine();
            DelPinoMartinaEventTask newTask = new DelPinoMartinaEventTask(taskText);
            newEvent.addTask(newTask);
        }

        events.add(newEvent);
        System.out.println("Evento añadido con éxito.");
    }

    public void deleteEvent() {
        System.out.print("Nombre del evento que desea eliminar: ");
        String nameEvent = input.nextLine();
        DelPinoMartinaEvent eventToRemove = null;
        for (DelPinoMartinaEvent event : events) {
            if (Objects.equals(event.getTitle(), nameEvent)) {
                eventToRemove = event;
                break;
            }
        }
        if (eventToRemove != null) {
            events.remove(eventToRemove);
            System.out.println("El evento ha sido eliminado con éxito.");
        } else {
            System.out.println("No se encontró el evento");
        }
    }

    public void eventsList() {
        if (events.isEmpty()) {
            System.out.println("No hay eventos registrados.");
            return;
        }

        System.out.println("Lista de eventos:");
        for (DelPinoMartinaEvent event : events) {
            System.out.println(event.toString());
        }
    }

    public void idDone() {
        System.out.print("Introduce el nombre del evento de la tarea:");
        String nameTask= input.nextLine();
        DelPinoMartinaEvent selectedEvent = events.stream()
                .filter(t -> t.getTitle().equals(nameTask))
                .findFirst()
                .orElse(null);

        if (selectedEvent == null) {
            System.out.println("No se encontró ningún evento con ese nombre");
            return;
        }

        if (selectedEvent.getTasks().isEmpty()) {
            System.out.println("El evento no tiene tareas registradas.");
            return;
        }

        System.out.println("Tareas del evento:");
        for (int i = 0; i < selectedEvent.getTasks().size(); i++) {
            DelPinoMartinaEventTask task = selectedEvent.getTasks().get(i);
            System.out.println((i + 1) + ". " + task.getText() + " (Completada: " + task.getCompleted() + ")");
        }

        System.out.print("Introduce el número de la tarea que deseas modificar: ");
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(input.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Por favor, introduce un número válido.");
            return;
        }

        if (taskIndex < 0 || taskIndex >= selectedEvent.getTasks().size()) {
            System.out.println("Número de tarea inválido.");
            return;
        }

        DelPinoMartinaEventTask selectedTask = selectedEvent.getTasks().get(taskIndex);
        selectedTask.setCompleted(!selectedTask.getCompleted());
        System.out.println("El estado de la tarea '" + selectedTask.getText() + "' ha sido actualizado a: " +
                (selectedTask.getCompleted() ? "Completada" : "No Completada"));
    }
}