package sample;

public class Uredi {

    int idUreda;
    String imeUreda;
    int idPodruznice;

    public Uredi(int idUreda, String imeUreda, int idPodruznice) {

        this.idUreda = idUreda;
        this.imeUreda = imeUreda;
        this.idPodruznice = idPodruznice;
    }

    public int getIdUreda() {
        return idUreda;
    }

    @Override
    public String toString() {
        return this.imeUreda;
    }
}