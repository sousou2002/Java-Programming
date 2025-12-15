
class Buffer {

    private Integer dato;

    public synchronized void producir(int x) throws InterruptedException {
        while (dato != null) {       // mientras esté lleno, esperar
            wait();                  // libera el monitor y pasa a WAITING
        }
        dato = x;
        notifyAll();                 // despierta consumidores
    }

    public synchronized int consumir() throws InterruptedException {
        while (dato == null) {       // mientras esté vacío, esperar
            wait();                  // WAITING
        }
        int x = dato;
        dato = null;
        notifyAll();                 // despierta productores
        return x;
    }
}

public class ProductorConsumidor {

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread productor = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                try {
                    buffer.producir(i);
                    System.out.println("Producido: " + i);
                    Thread.sleep(200); // TIMED_WAITING
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }, "Productor");

        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 1; i <= 3; i++) {
                    int x = buffer.consumir();
                    System.out.println("Consumido: " + x);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumidor");

        productor.start();
        consumidor.start();

        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("TERMINATED ambos hilos");
    }
}
