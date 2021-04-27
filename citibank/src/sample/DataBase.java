package sample;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataBase {

    public Connection databaseLink;
    public Regije regija;
    public Podruznice podruznica;
    public Uredi ured;
    public Mikrokrediti mikrokredit;
    public Isplate isplate;

    public ArrayList<Mikrokrediti> dohvatiSveMikrokredite(LocalDate datumOd, LocalDate datumDo, int regija,
                                                          int podruznica, int ured){

        ArrayList<Mikrokrediti> listaMikrokredita = new ArrayList<>();
        databaseLink = this.getConnection();
        String querry = "SELECT * FROM mikrokrediti WHERE " +
                        "Datum_Isplate BETWEEN '" + datumOd + "' AND '" + datumDo + "' AND " +
                        "ID_Regije LIKE '" + regija + "' AND ID_Podruznice LIKE '" + podruznica + "' " +
                        "AND ID_Uredi LIKE '" + ured + "'";
        try {
            Statement statement = databaseLink.createStatement();
            statement.executeQuery(querry);
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()) {
                mikrokredit = new Mikrokrediti(resultSet.getInt("ID"),
                                               resultSet.getString("Broj_Ugovora"),
                                               resultSet.getObject("Datum_Isplate",LocalDate.class),
                                               resultSet.getString("Iznos_Mikrokredita"),
                                               resultSet.getInt("ID_Regije"),
                                               resultSet.getInt("ID_Podruznice"),
                                               resultSet.getInt("ID_Uredi"));
                listaMikrokredita.add(mikrokredit);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return listaMikrokredita;
    }

    public ArrayList<Isplate> dohvatiSveMikrokrediteRegijaIsplata(LocalDate datumOd, LocalDate datumDo, int regija){

        ArrayList<Isplate> listaIsplata = new ArrayList<>();
        databaseLink = this.getConnection();
        String querry = "SELECT COUNT(Broj_Ugovora) AS Broj, MAX(Iznos_Mikrokredita) AS Maksimum, SUM(Iznos_Mikrokredita) AS Suma," +
                "(SUM(Iznos_Mikrokredita)/COUNT(Broj_Ugovora)) AS Prosjek FROM mikrokrediti WHERE " +
                "DATUM_Isplate BETWEEN '" + datumOd + "' AND '" + datumDo + "' AND ID_Regije LIKE '" + regija + "'";

        try {
            Statement statement = databaseLink.createStatement();
            statement.executeQuery(querry);
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()) {
                isplate = new Isplate(resultSet.getInt("Broj"),
                                      resultSet.getInt("Maksimum"),
                                      resultSet.getInt("Suma"),
                                      resultSet.getInt("Prosjek"));

                listaIsplata.add(isplate);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return listaIsplata;
    }

    public ArrayList<Isplate> dohvatiSveMikrokreditePodruznicaIsplata(LocalDate datumOd, LocalDate datumDo, int podruznica){

        ArrayList<Isplate> listaIsplata = new ArrayList<>();
        databaseLink = this.getConnection();
        String querry = "SELECT COUNT(Broj_Ugovora) AS Broj, MAX(Iznos_Mikrokredita) AS Maksimum, SUM(Iznos_Mikrokredita) AS Suma," +
                "(SUM(Iznos_Mikrokredita)/COUNT(Broj_Ugovora)) AS Prosjek FROM mikrokrediti WHERE " +
                "DATUM_Isplate BETWEEN '" + datumOd + "' AND '" + datumDo + "' AND ID_Podruznice LIKE '" + podruznica + "'";

        try {
            Statement statement = databaseLink.createStatement();
            statement.executeQuery(querry);
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()) {
                isplate = new Isplate(resultSet.getInt("Broj"),
                        resultSet.getInt("Maksimum"),
                        resultSet.getInt("Suma"),
                        resultSet.getInt("Prosjek"));

                listaIsplata.add(isplate);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return listaIsplata;
    }


    public ArrayList<Regije> dohvatiSveRegije() {

        ArrayList<Regije> listaRegija = new ArrayList<>();
        databaseLink = this.getConnection();
        String querry = "SELECT * FROM regije";

        try {
            Statement statement = databaseLink.createStatement();
            statement.executeQuery(querry);
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()) {
                regija = new Regije(resultSet.getInt("ID"),
                                    resultSet.getString("Ime"));
                listaRegija.add(regija);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return listaRegija;
    }

    public ArrayList<Podruznice> dohvatiSvePodruznice() {

        ArrayList<Podruznice> listaPodruznica = new ArrayList<>();
        databaseLink = this.getConnection();
        String querry = "SELECT * FROM podruznice";
        try {
            Statement statement = databaseLink.createStatement();
            statement.executeQuery(querry);
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()) {
                podruznica = new Podruznice(resultSet.getInt("ID"),
                                            resultSet.getString("Ime"),
                                            resultSet.getInt("ID_Regije"));
                listaPodruznica.add(podruznica);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return listaPodruznica;
    }

    public ArrayList<Uredi> dohvatiSveUrede() {

        ArrayList<Uredi> listaUreda = new ArrayList<>();
        databaseLink = this.getConnection();
        String querry = "SELECT * FROM uredi";

        try {
            Statement statement = databaseLink.createStatement();
            statement.executeQuery(querry);
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()) {
                ured = new Uredi(resultSet.getInt("ID"),
                                 resultSet.getString("Ime"),
                                 resultSet.getInt("ID_Podruznice"));
                listaUreda.add(ured);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return listaUreda;
    }

    public ArrayList<Podruznice> dohvatiPodruznice(int idRegije) {

        ArrayList<Podruznice> listaPodruznica = new ArrayList<>();
        databaseLink = this.getConnection();
        String querry = "SELECT * FROM podruznice WHERE  ID_Regije LIKE '" + idRegije + "'";

        try {
            Statement statement = databaseLink.createStatement();
            statement.executeQuery(querry);
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()) {
                podruznica = new Podruznice(resultSet.getInt("ID"),
                                            resultSet.getString("Ime"),
                                            resultSet.getInt("ID_Regije"));
                listaPodruznica.add(podruznica);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return listaPodruznica;
    }

    public ArrayList<Uredi> dohvatiUrede(int idPodruznice) {

        ArrayList<Uredi> listaUreda = new ArrayList<>();
        databaseLink = this.getConnection();
        String querry = "SELECT * FROM uredi WHERE  ID_Podruznice LIKE '" + idPodruznice + "'";

        try {
            Statement statement = databaseLink.createStatement();
            statement.executeQuery(querry);
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()) {
                ured = new Uredi(resultSet.getInt("ID"),
                                 resultSet.getString("Ime"),
                                 resultSet.getInt("ID_Podruznice"));
                listaUreda.add(ured);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return listaUreda;
    }

    public Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/mi_bospo";
        String databaseUser = "root";
        String databasePassword = "1234";

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}

