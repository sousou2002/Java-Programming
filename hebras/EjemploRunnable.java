
class MiTarea implements Runnable {
    @Override
    public void run() {
        System.out.println("MiTarea en: " + Thread.currentThread().getName());
        // trabajo…
    }
}

public class EjemploRunnable {
    public static void main(String[] args) {
        Runnable tarea = new MiTarea();
        Thread t = new Thread(tarea, "Worker-2");
        t.start();

        Runnable tarea2 = new MiTarea();
        Thread t2 = new Thread(tarea2, "Worker-3");
        t2.start();
    }
}
