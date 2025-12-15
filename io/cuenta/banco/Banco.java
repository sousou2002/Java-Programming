package cuenta.banco;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import cuenta.Cuenta;
import cuenta.CuentaAhorro;
import cuenta.CuentaCorriente;

public class Banco {
    private Cuenta[] cuentas;
    private static final int MAX_CUENTAS = 5;
    private int num_cuentas;

    public Banco() {
        cuentas = new Cuenta[MAX_CUENTAS];
        num_cuentas = 0;
    }

    public void abrirCuenta(Cuenta c) {
      if (num_cuentas==cuentas.length) {
        System.out.println("No caben mas cuentas en el banco");
      } else {
        cuentas[num_cuentas] = c;
        num_cuentas++;
      }
    }

    public void cerrarCuenta(int ccc) {
        int pos_a_eliminar = 0;
        boolean encontrado = false;
        while (pos_a_eliminar < num_cuentas && !encontrado) {
            encontrado = ccc == cuentas[pos_a_eliminar].getCCC();
            if (!encontrado) {
                pos_a_eliminar++;
            }
        }
        if (encontrado) {
            cuentas[pos_a_eliminar] = cuentas[num_cuentas-1];
            cuentas[num_cuentas-1] = null;
            num_cuentas--;
        }
    }

    public void actualizar() {
        for (int i=0; i<num_cuentas; i++) {
            cuentas[i].actualizar();
        }
    }

    public void imprimirCuentas() {
        for (int i=0; i<num_cuentas; i++) {
            System.out.println("Cuenta en posicion "+i+": "+cuentas[i]);
        }
    }

    public void guardarFicheroObj(String fname) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fname)));) {
            out.writeInt(num_cuentas);
            for (int i=0; i<num_cuentas; i++) {
                out.writeObject(cuentas[i]);
            }
        }
    }
    public void leerFicheroObj(String fname) throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fname)));) {
            int nc = in.readInt();
            for (int i=0; i<nc; i++) {
                Object o = in.readObject();
                Cuenta c = (Cuenta)o;
                abrirCuenta(c);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
    public void guardarFicheroTxt(String fname) throws IOException {
        try (PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(fname)));) {
            for (int i=0; i<num_cuentas; i++) {
                salida.println(cuentas[i].stringRepr());
            }
        }
    }

    public void leerFicheroTxt(String fname) throws IOException {
        try (BufferedReader entrada = new BufferedReader(new FileReader(fname));) {
            boolean continuar = true;
            while (continuar) {
                String linea = entrada.readLine();
                continuar = linea!=null;
                if (continuar) {
                    String[] tokens = linea.split(" ");
                    String className = tokens[0];
                    int ccc = Integer.parseInt(tokens[1]);
                    double balance = Double.parseDouble(tokens[2]);
                    Cuenta c;
                    if (className.equals("Cuenta")) {
                        c = new Cuenta(ccc);
                    } else if (className.equals("CuentaAhorro")) {
                        double interes = Double.parseDouble(tokens[3]);
                        c = new CuentaAhorro(ccc, interes);
                    } else if (className.equals("CuentaCorriente")) {
                        double limite = Double.parseDouble(tokens[3]);
                        c = new CuentaCorriente(ccc, limite);
                    } else {
                        throw new IOException("Nombre de clase de cuenta no reconocido: "+className);
                    }
                    if (balance>0)
                        c.deposito(balance);
                    else if (balance < 0) {
                        c.retirada(-balance);
                    }
                    abrirCuenta(c);
                }
            }
        }
    }

}



// https://github.com/sousou2002/Java-Programming.git