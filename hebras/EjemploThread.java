
class MiHilo extends Thread {
    @Override
    public void run() {
        System.out.println("MiHilo ejecutándose en: " + Thread.currentThread().getName());
        try {
            Thread.sleep(500); // TIMED_WAITING
        } catch (InterruptedException e) {
            System.out.println("Interrumpido");
            Thread.currentThread().interrupt(); // volver a marcar la interrupción
        }
    }
}

public class EjemploThread {
    public static void main(String[] args) {
        Thread t = new MiHilo();
        t.setName("Worker-1");
        t.start();                // Pasa de NEW a RUNNABLE
        try {
            t.join();             // main entra en WAITING hasta que t termina
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Terminado");
    }
}
