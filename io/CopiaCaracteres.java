
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopiaCaracteres {

    public static void main(String[] args) {
        // Esto es lo que hay que hacer cuando no tenemos un “try-with-resources”
        FileReader inputStream = null;
        FileWriter outputStream = null;
        try {
            inputStream = new FileReader("input.txt");
            outputStream = new FileWriter("characteroutput.txt");
            try {
                int c;
                while ((c = inputStream.read()) != -1) {
                    outputStream.write(c);
                }
            } finally {
                inputStream.close();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se ha podido copiar el fichero.");
        }
    }
}
