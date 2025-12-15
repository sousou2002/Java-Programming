
import java.util.*;

public class Carrito {

    private List<String> productos;

    public Carrito() {
        productos = new ArrayList<>();
    }

    public void añadirProducto(String producto, Catalogo catalogo) {
        //System.out.println("Tus productos en el carrito:");
        if (catalogo.existeProducto(producto)) {
            productos.add(producto);
            System.out.println("Producto agregado al carrito: + " + producto);
        } else {
            System.out.println("Producto no disponible: " + producto);
        }
    }

    public void mostrarCarrito() {
        System.out.println("Contenido del carrito:");
        // Bucle for-each para recorrer y mostrar los productos almacenados en el carrito
        for (String producto : productos) {
            System.out.println("- " + producto);
        }
    }

    public double calcularTotal(PrecioService precios) {
        double total = 0.0;
        for (String producto : productos) {
            total += precios.getPrecio(producto);
        }
        return total;
    }

}
