
import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {

    protected Socket socket;
    protected boolean heTerminado;

    public Cliente(Socket s) {
        socket = s;
        heTerminado = false;
    }

    public synchronized boolean heTerminado() {
        return heTerminado;
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            while (true) {
                String linea = br.readLine();
                if (linea == null) {
                    break;
                }
                System.out.println("Me ha llegado del servidor: <" + linea + ">");
            }
        } catch (SocketException se) {
            if (!socket.isClosed()) {
                se.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            synchronized (this) {
                heTerminado = true;

            }

        }
    }

    public static void main(String[] args) {
        Cliente cliente = null;
        try (Socket socket = new Socket("localhost", 4321); Scanner scanner = new Scanner(System.in);) {
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

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
