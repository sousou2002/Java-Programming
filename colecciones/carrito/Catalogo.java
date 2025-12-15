import java.util.*;

public class Catalogo {

    private final Set<String> productos;

    public Catalogo() {
        productos = new HashSet<>();

        productos.add("Manzana");
        productos.add("Naranja");
        productos.add("Piña");
        productos.add("Cereza");
        productos.add("Pera");
    }

    public boolean existeProducto(String producto) {
        return productos.contains(producto);
    }

    public Set<String> getProductos() {
        return productos;
    }

}
