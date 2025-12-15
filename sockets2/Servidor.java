
import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(4321)) {
            System.out.println("Conexion establecida en el puerto 4321");
            while (true) {
                try (Socket c = s.accept(); 
                PrintWriter out = new PrintWriter(c.getOutputStream(), true); 
                BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()))) 
                {//Intercambio de datos
                    String inputLine;
                    boolean continuar = true;
                    while(continuar){
                        //Leer datos del cliente
                        inputLine = in.readLine();
                        continuar = inputLine != null;
                        if(continuar){
                            //Procesar datos recibidos
                            File f = new File(inputLine);
                            if(f.exists()){
                                try(BufferedReader br = new BufferedReader(new FileReader(f)))
                                {
                                    int numlineas = 0;
                                    while(br.readLine() != null){
                                        numlineas++;
                                    }out.println(numlineas);

                                }catch(Exception e){
                                    out.println("Error al leer el fichero:"+e.getMessage());

                                }
                            }else{
                                out.println("El fichero no existe.");
                            }

                        }

                    }

                } catch (IOException e) {
                    e.getMessage();
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}
