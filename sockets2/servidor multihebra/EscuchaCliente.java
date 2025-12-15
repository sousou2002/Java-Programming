
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class EscuchaCliente implements Runnable {

    protected Socket cliente_a_escuchar;
    protected BufferedReader input_chats;
    protected Servidor servidor;

    public EscuchaCliente(Servidor s, Socket c) throws IOException {
        servidor = s;
        cliente_a_escuchar = c;
        input_chats = new BufferedReader(new InputStreamReader(c.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String linea = input_chats.readLine();
                if (linea == null) {
                    break;
                }
                //llevar la cuenta del numero de chats: usamos synchronized para acceder a num_chats.numero sin problemas de acceso concurrente
                Numero num_chats = servidor.getNumChats();
                synchronized (num_chats) {
                    num_chats.numero += 1;
                    System.out.println("Numero de chats que han llegado: " + num_chats.numero);
                }
                Map<Socket, PrintWriter> map = servidor.getClientes();
                //hacemos una copia del mapa para evitar problemas de concurrencia si un cliente es cerrado y eliminado del mapa original
                synchronized (servidor.getClientes()) {
                    //la copia debe hacerse dentro de un bloque synchronized para que no haya otro hilo añadiendo/quitando elementos al mapa mientras lo copiamos
                    map = new HashMap<>(map);
                }
                for (Map.Entry<Socket, PrintWriter> e : servidor.getClientes().entrySet()) {
                    boolean es_mi_cliente = cliente_a_escuchar.equals(e.getKey());
                    if (!es_mi_cliente) {
                        PrintWriter w = e.getValue();
                        //usamos un bloque synchronized: si llega otro chat de otro cliente y desde otro hilo lo escribimos concurrentemente, queremos evitar que los mensajes se intercalen
                        synchronized (w) {
                            try {
                                w.println(linea);
                                w.flush();
                            } catch (Exception ex) {
                                // No hacer nada si hay algún problema
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ha ocurrido un error o el cliente ha cerrado la conexión: eliminar el socket del mapa
        Map<Socket, PrintWriter> map = servidor.getClientes();
        //usamos un bloque sincronized para no modificarlo mientras otro hilo lee el mapa
        synchronized (map) {
            map.remove(cliente_a_escuchar);
        }
    }

}
