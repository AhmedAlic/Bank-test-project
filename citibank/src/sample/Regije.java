package sample;

public class Regije {

    int idRegije;
    String imeRegije;

    public Regije(int idRegije, String imeRegije) {

        this.idRegije = idRegije;
        this.imeRegije = imeRegije;
    }

    public int getIdRegije() {
        return idRegije;
    }

    @Override
    public String toString() {
        return this.imeRegije;
    }
}