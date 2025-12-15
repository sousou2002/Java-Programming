package cuenta;

public class CuentaCorriente extends Cuenta  {
    private double limiteBalance;

    public CuentaCorriente(int ccc, double limite) {
        super(ccc);
        limiteBalance = limite;
    }

    @Override
    public void retirada(double sum)
    {
        if (getBalance()-sum>-limiteBalance) 
            super.retirada(sum);
        else
            System.err.println("No puedes retirar mas del limite!!");
    }

    @Override
    public void actualizar() {
        if (getBalance()<0) {
            System.err.println("Cuenta "+getCCC()+" en descubierto: mandando carta de pago!!!");
        } else {
            super.actualizar();
        }
    }

    public double getLimiteBalance() {
        return limiteBalance;
    }

    public String stringRepr() {
        return "CuentaCorriente "+getCCC()+" "+getBalance()+" "+limiteBalance;
    }
}
