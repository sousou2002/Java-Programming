
import cuenta.Cuenta;
import cuenta.banco.Banco;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ServidorUDP {

    public static void main(String[] args) {
        Banco banco = new Banco();
        try (DatagramSocket socket = new DatagramSocket(4321)) {
            byte[] bufferReception = new byte[10240];
            while (true) {
                //Recepcion del paquete o peticion
                DatagramPacket datagrama = new DatagramPacket(bufferReception, bufferReception.length);
                socket.receive(datagrama);
                //Proceso de la peticion
                ByteArrayInputStream bis = new ByteArrayInputStream(bufferReception);
                ObjectInputStream ois = new ObjectInputStream(bis);
                Cuenta cuenta = (Cuenta) ois.readObject();
                banco.abrirCuenta(cuenta);
                System.out.println("Hemos recibido una cuenta nueva! Listado de cuentas del banco: ");
                banco.imprimirCuentas();

                //Envio de la respuesta
                byte[] bufferEnvio = "Muchas gracias por enviar esta cuenta!".getBytes(StandardCharsets.UTF_8);
                int port = datagrama.getPort();
                InetAddress addr = datagrama.getAddress();
                datagrama = new DatagramPacket(bufferEnvio, bufferEnvio.length, addr, port);
                socket.send(datagrama);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
