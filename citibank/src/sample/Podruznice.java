package sample;

public class Podruznice {

    int idPodruznice;
    String imePodruznice;
    int idRegije;

    public Podruznice(int idPodruznice, String imePodruznice, int idRegije) {

        this.idPodruznice = idPodruznice;
        this.imePodruznice = imePodruznice;
        this.idRegije = idRegije;
    }

    public int getIdPodruznice() {
        return idPodruznice;
    }

    @Override
    public String toString() {
        return this.imePodruznice;
    }

}

