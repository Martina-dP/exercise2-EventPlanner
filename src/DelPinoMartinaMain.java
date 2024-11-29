import java.time.LocalDate;
import java.util.Scanner;

public class DelPinoMartinaMain {
    public static void main(String[] args) {
        DelPinoMartinaMain program = new DelPinoMartinaMain();
        program.inicio();
    }
    public void inicio() {

        DelPinoMartinaEvent submitproject = new DelPinoMartinaEvent(
                "Entrega  segundo proyecto",
                LocalDate.of(2024, 12, 13),
                DelPinoMartinaEvent.Priority.HIGH
        );

        DelPinoMartinaEventTask endMenu = new DelPinoMartinaEventTask(
                false,
                "Crear las funciones para cada opcion"
        );

        submitproject.addTask(endMenu);

        EventPlanner();
    }

    public void EventPlanner(){

        int opcion ;
        boolean end = false;
        Scanner input = new Scanner(System.in);

        System.out.println("Bienvenido a Event Planner: " );

        while (true) {

            System.out.println(" Seleccione una opción: ");
            System.out.println("1 = Añadir evento");
            System.out.println("2 =  Borrar evento");
            System.out.println("3 =   Listar eventos");
            System.out.println("4 = Marcar/desmarcar tarea de un evento como completada");
            System.out.println("5 = Salir");

            opcion = input.nextInt();

            switch (opcion) {
                case 1: {
                    System.out.println("Añadir " );
                    break;
                }
                case 2: {
                    System.out.println("Borrar");
                    break;
                }
                case 3: {
                    System.out.println("Listar");
                    break;
                }
                case 4: {
                    System.out.println("Marcar/Desmarcar");
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
}