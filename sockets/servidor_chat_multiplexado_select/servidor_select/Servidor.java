package servidor_select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Set;

public class Servidor {
  protected Set<SocketChannel> clientes;
  protected int num_chats;
  protected Selector selector;
  protected ServerSocketChannel servsock;
  protected ByteBuffer buffer;
  
  public Servidor() throws IOException {
    clientes = new HashSet<>();
    num_chats = 0;

    servsock = ServerSocketChannel.open();
    // El canal NO se bloquea en operaciones Read/Write
    servsock.configureBlocking(false); //para select(): modo no bloqueante!!!

    // Vincular a puerto 5678 en todas las interfaces
    servsock.bind(new InetSocketAddress("0.0.0.0", 5678));

    // Crear selector para multiplexacion
    selector = Selector.open();
    // Registrar para que selector pueda detectar nuevas conexiones
    servsock.register(selector, SelectionKey.OP_ACCEPT); //registramos para detectar servsock.accept()
    
    // Crear buffer compartido
    buffer = ByteBuffer.allocate(4096);
  }
  
  public void registrarCliente() throws IOException {
    SocketChannel cliente = servsock.accept();
    cliente.configureBlocking(false); //para select(): modo no bloqueante!!!
    cliente.register(selector, SelectionKey.OP_READ); //registramos para detectarcliente.read()
    clientes.add(cliente);
  }
  
  public void procesarEventoCliente(SelectionKey key) throws IOException {
    SocketChannel cliente_con_evento = (SocketChannel)key.channel();
    // escribimos en el buffer lo que ha enviado el cliente
    int num_bytes_leidos;
    try {
      num_bytes_leidos = cliente_con_evento.read(buffer);
    } catch (Exception e) {
      num_bytes_leidos = -1;
    }
    if (num_bytes_leidos==-1) { //si un cliente se desconecta, num_bytes_leidos==-1
      clientes.remove(cliente_con_evento);
      key.cancel(); //des-registramos cliente_con_evento.read()
      cliente_con_evento.close();      
    } else {
      num_chats++;
      System.out.println("Numero de chats que han llegado: "+num_chats);
      for (SocketChannel cliente: clientes) {
        boolean es_cliente_con_evento = cliente.equals(cliente_con_evento);
        if (!es_cliente_con_evento) {
          buffer.flip(); // reseteamos el índice del buffer para que cliente.write(buffer) lea lo que se escribió antes al ejecutar cliente_con_evento.read(buffer)
          cliente.write(buffer);
        }
      }
      buffer.clear(); //ya no vamos a usarlo más, así que vaciamos el buffer
    }
  }
  
  public void runServidor() throws IOException{
    while (true) {
      selector.select();
      Set<SelectionKey> selected = selector.selectedKeys();
      for (SelectionKey key: selected) {
        if (key.isAcceptable()) {
          registrarCliente();
        } else if (key.isReadable()) {
          procesarEventoCliente(key);
        }
      }
      selected.clear(); // hay que eliminar todos los sockets que hemos procesado del conjunto de seleccionados
    }
  }

  public static void main(String[] args) {
    try {
      new Servidor().runServidor();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
