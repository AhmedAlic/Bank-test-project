package sample;

import java.time.LocalDate;

public class Mikrokrediti {

    int idMikrokredita;
    String brojUgovora;
    LocalDate datumIsplate;
    String iznosMikrokredita;
    int idRegije;
    int idPodruznice;
    int idUreda;

    public Mikrokrediti(int id, String ugovor, LocalDate datum, String iznos, int regija, int podruznica, int ured) {

        this.idMikrokredita = id;
        this.brojUgovora = ugovor;
        this.datumIsplate = datum;
        this.iznosMikrokredita = iznos;
        this.idRegije= regija;
        this.idPodruznice = podruznica;
        this.idUreda = ured;
    }

    public int getIdMikrokredita() {
        return idMikrokredita;
    }

    public void setIdMikrokredita(int idMikrokredita) {
        this.idMikrokredita = idMikrokredita;
    }

    public String getBrojUgovora() {
        return brojUgovora;
    }

    public void setBrojUgovora(String brojUgovora) {
        this.brojUgovora = brojUgovora;
    }

    public LocalDate getDatumIsplate() {
        return datumIsplate;
    }

    public void setDatumIsplate(LocalDate datumIsplate) {
        this.datumIsplate = datumIsplate;
    }

    public String getIznosMikrokredita() {
        return iznosMikrokredita;
    }

    public void setIznosMikrokredita(String iznosMikrokredita) {
        this.iznosMikrokredita = iznosMikrokredita;
    }

    public int getIdRegije() {
        return idRegije;
    }

    public void setIdRegije(int idRegije) {
        this.idRegije = idRegije;
    }

    public int getIdPodruznice() {
        return idPodruznice;
    }

    public void setIdPodruznice(int idPodruznice) {
        this.idPodruznice = idPodruznice;
    }

    public int getIdUreda() {
        return idUreda;
    }

    public void setIdUreda(int idUreda) {
        this.idUreda = idUreda;
    }

    @Override
    public String toString() {
        String string = "" + idMikrokredita + ", " + brojUgovora + ", " + datumIsplate + ", " +
                         "" + iznosMikrokredita + " " +  idRegije + ", " +  idRegije + ", " +  idUreda + "";
        return string;
    }
}

