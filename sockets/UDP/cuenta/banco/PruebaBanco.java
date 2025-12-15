package cuenta.banco;

import cuenta.*;

public class PruebaBanco {
  public static void main(String[] args) {
    Banco b = new Banco();

    Cuenta c1 = new Cuenta(123456);
    Cuenta c2 = new CuentaAhorro(123457, 0.1);
    Cuenta c3 = new Cuenta(123458);
    Cuenta c4 = new CuentaCorriente(123459, 50);

    b.abrirCuenta(c1);
    b.abrirCuenta(c2);
    b.abrirCuenta(c3);
    b.abrirCuenta(c4);

    c1.deposito(10);
    c2.deposito(20);
    c3.deposito(30);
    c4.deposito(40);

    System.out.println("Mostrando cuentas:");
    b.imprimirCuentas();

    b.cerrarCuenta(c1.getCCC());

    System.out.println("Mostrando cuentas tras borrar c1:");
    b.imprimirCuentas();

    c4.retirada(50);

    b.actualizar();
    System.out.println("Mostrando cuentas tras actualizar:");
    b.imprimirCuentas();
  }
}
