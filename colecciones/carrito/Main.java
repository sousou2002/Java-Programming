// Listas Ordenadas (permiten duplicados)
// Clases principales: ArrayList, LinkedList

// Conjuntos Desordenados (no permiten duplicados)
// Clases principales: HashSet, LinkedHashSet, TreeSet
// Almacena psres clave-valor, las claves son unicas
// Clases principales: HashMap, LinkedHashMap, TreeMap

/*
se desea implementar un carrito de compra para una tienda en linea. La tienda
tiene un catalogo de productos disponibles, cada producto tiene un precio asociado. 
El carrito del usuario almacena los productos añadidos que pueden ser repetidos.
El sistema debe mostrar el catalogo, añadir producto al carrito, mostrar el contenido del carrito 
y calcular el precio total a pagar.

Para ello: usaremos Set para el catalogo de productos, Map para los precios, List para el carrito.
 */
public class Main {

    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        PrecioService precios = new PrecioService();
        Carrito carrito = new Carrito();

        System.out.println("Catalogo de productos disponibles: ");
        for (String producto : catalogo.getProductos()) {
            System.out.println("- " + producto);
        }

        carrito.añadirProducto("Piña", catalogo);
        carrito.añadirProducto("Platano", catalogo);

        carrito.añadirProducto("Cereza", catalogo);

        carrito.mostrarCarrito();
        System.out.println("Total a pagar: " + carrito.calcularTotal(precios));

    }

}
