import java.util.Objects;
public class Restaurante implements Comparable<Restaurante>
{
    private String cif;
    private int startYear;

    public Restaurante(String CIF, int year){
        cif = CIF;
        startYear = year;
    }

    @Override
    public int compareTo(Restaurante o){
        int r = cif.compareTo(o.cif);
        if(r==0){
            r = Integer.compare(startYear, o.startYear);
        }
        return r;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Restaurante){
            Restaurante r = (Restaurante)o;
            return cif.equals(r.cif) && startYear == r.startYear;
        }else{
            return false;
        }
    }

    @Override 
    public int hashCode(){
        return Objects.hash(cif, startYear);
    }

    @Override
    public String toString(){
        return "Restaurante(cif="+cif+", year="+startYear+")";
    }

}