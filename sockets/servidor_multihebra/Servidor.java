package servidor_multihebra;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.io.PrintWriter;

public class Servidor {
  //para guardar el PrintWriter que creamos para escribir a cada cliente
  protected Map<Socket, PrintWriter> clientes;
  //para llevar la cuenta del número de chats
  protected Numero num_chats;
  
  public Servidor() {
    clientes = new HashMap<>();
    num_chats = new Numero();
  }
  
  public Map<Socket, PrintWriter> getClientes() {
    return clientes;
  }
  
  public Numero getNumChats() {
    return num_chats;
  }

  public void runServidor() {
    try (
      ServerSocket servsock = new ServerSocket();
    ) {
      servsock.bind(new InetSocketAddress("0.0.0.0", 5678));
      while (true) {
        Socket cliente = servsock.accept();
        PrintWriter w = new PrintWriter(cliente.getOutputStream());
        //usamos un bloque synchronized para evitar modificar el mapa mientras se lee en otro hilo
        synchronized (clientes) {
          clientes.put(cliente, w);
        }
        Thread thread = new Thread(new EscuchaCliente(this, cliente));
        thread.start();
        //no me importa cuándo terminará el hilo que se acaba de lanzar. No hace falta thread.join()
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.exit(0);
  }

  public static void main(String[] args) {
    new Servidor().runServidor();
  }

}
