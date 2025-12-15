
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiaBytes {

    public static void main(String[] args) {
        // Esto es un “try-with-resources”
        try (FileInputStream in = new FileInputStream("entrada.txt"); 
        FileOutputStream out = new FileOutputStream("salida.txt");) 
        {
            int c;
            while ((c = in.read()) != -1) { //si devuelve -1 es fin de fichero
                out.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("No se ha podido copiar el fichero.");
        }
    }
}
