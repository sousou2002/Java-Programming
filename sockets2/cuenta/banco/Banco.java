package cuenta.banco;

import cuenta.Cuenta;

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
    
}
