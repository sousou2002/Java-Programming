import java.util.*;

public class PrecioService {

    private final Map<String, Double> precios;

    public PrecioService() {
        precios = new HashMap<>();

        precios.put("Manzana", 0.5);
        precios.put("Naranja", 0.7);
        precios.put("Piña", 1.5);
        precios.put("Cereza", 2.0);
        precios.put("Pera", 0.6);
    }

    public Double getPrecio(String producto) {
        return precios.get(producto);
    }

}
