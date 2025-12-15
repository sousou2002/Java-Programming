
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class LastCharCount {

    private SortedMap<Character, Integer> mapa;

    public LastCharCount() {
        mapa = new TreeMap<>();
    }

    public void procesarPalabra(String palabra) {
        char c = palabra.charAt(palabra.length() - 1);
        if (mapa.containsKey(c)) {
            mapa.put(c, mapa.get(c) + 1);
        } else {
            mapa.put(c, 1);
        }
    }

    public void escribirFrecuencias(PrintWriter pw) {
        for (Map.Entry<Character, Integer> entry : mapa.entrySet()) {
            pw.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        LastCharCount counter = new LastCharCount();
        String directorio = "C:\\Users\\siham.ennahal\\Downloads\\java\\IO\\"; 
        try (
                BufferedReader reader = new BufferedReader(new FileReader(directorio + "entrada.txt")); 
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(directorio + "salida.txt")));) 
        {
            boolean continuar = true;
            while (continuar) 
            {
                String linea = reader.readLine();
                continuar = linea != null;
                if (linea != null) {
                    String[] palabras = linea.split(" ");
                    for (String palabra : palabras) {
                        if (palabra.length() > 0) {
                            counter.procesarPalabra(palabra.toLowerCase());
                        }

                    }
                }
            }
            counter.escribirFrecuencias(pw);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de E/S");
        }
    }
}
