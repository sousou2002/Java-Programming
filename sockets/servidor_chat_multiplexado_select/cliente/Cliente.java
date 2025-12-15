package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente implements Runnable {
  protected Socket socket;
  
  /*esto no es estrictamente necesario para el correcto funcionamiento de
  la comunicación con el servidor; solamente es necesario para que la hebra
  que lee de teclado termine tras ejecutar scanner.nextLine(), si se ha cortado la
  conexión con el servidor (no siempre se lanza una excepción al escribir en el
  OutputStream del socket)*/
  protected boolean heTerminado; 
  
  public Cliente(Socket s) {
    socket = s;
    heTerminado = false;
  }
  
  public synchronized boolean heTerminado() {
    return heTerminado;
  }
  
  public void run() {
    try (
      BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    ) {
      while (true) {
        String linea = br.readLine();
        if (linea==null) {
          break;
        }
        System.out.println("Me ha llegado del servidor: <"+linea+">");
      }
    } catch (SocketException e) {
      //readLine() lanzará esta excepción si el socket ha sido cerrado, pero en tal caso no se trata de un error
      if (!socket.isClosed()) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      synchronized(this) {
        heTerminado = true;
      }
    }
  }
  
  
  public static void main(String[] args) {
    Cliente cliente = null;
    try (
        Socket socket = new Socket("127.0.0.1", 5678);
        Scanner scanner = new Scanner(System.in); 
        ){
          PrintWriter w = new PrintWriter(socket.getOutputStream());
          cliente = new Cliente(socket); 
          Thread thread = new Thread(cliente);
          thread.start();
          while (!cliente.heTerminado()) {
            String linea = scanner.nextLine();
            if (linea.equals("EXIT")) {
              break;
            }
            if (!cliente.heTerminado()) {
              w.println(linea);
              w.flush();
            }
          }
      
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
