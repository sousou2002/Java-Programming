package cuenta;

public class CuentaAhorro extends Cuenta {
    private double tasaInteres;

    public CuentaAhorro(int ccc, double i) {
        super(ccc);
        tasaInteres = i;
    }

    public void addInteresMensual() {
        deposito(tasaInteres*getBalance());
    }

    @Override
    public void actualizar() {
        addInteresMensual();
        super.actualizar();
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public String stringRepr() {
        return "CuentaAhorro "+getCCC()+" "+getBalance()+" "+tasaInteres;
    }

}
