
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunnableConExecutor {
    public static void main(String[] args) {
        // Pool fijo de 3 hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Enviar varias tareas Runnable
        for (int i = 1; i <= 5; i++) {
            int tareaId = i;
            executor.submit(() -> {
                String nombreHilo = Thread.currentThread().getName();
                System.out.printf("Tarea %d ejecutándose en %s%n", tareaId, nombreHilo);
                try {
                    TimeUnit.MILLISECONDS.sleep(300); // Simular trabajo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // restablecer flag de interrupción
                    System.out.printf("Tarea %d interrumpida%n", tareaId);
                }
                System.out.printf("Tarea %d completada%n", tareaId);
            });
        }

        // Cierre ordenado: no acepta más tareas, pero termina las pendientes
        executor.shutdown();
        try {
            // Espera hasta 5 segundos a que finalice todo
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                // Si no terminó en tiempo, forzar cierre
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Pool cerrado correctamente.");
    }
}
