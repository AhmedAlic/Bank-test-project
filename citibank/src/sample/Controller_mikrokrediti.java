package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller_mikrokrediti implements Initializable {

    // Tipke mikokrediti.fxml

    @FXML
    DatePicker datumIsplateMikrokredita_tipka;
    @FXML
    TextField brojUgovora_tipka;
    @FXML
    TextField iznosMK_tipka;
    @FXML
    ChoiceBox<Regije> regijaMikrokredita_tipka;
    @FXML
    ChoiceBox<Podruznice> podruznicaMikrokredita_tipka;
    @FXML
    ChoiceBox<Uredi> uredMikrokredita_tipka;
    @FXML
    Button napustiTipka;
    public DataBase connectNow = new DataBase();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        regijaMikrokredita_tipka.getItems().addAll(connectNow.dohvatiSveRegije());
        podruznicaMikrokredita_tipka.getItems().addAll(connectNow.dohvatiSvePodruznice());
        uredMikrokredita_tipka.getItems().addAll(connectNow.dohvatiSveUrede());
    }

    // Metoda za promjene podruznica pri promjenama regija u choiceBoxu

    public void promjenaRegije() {

        podruznicaMikrokredita_tipka.getItems().setAll(connectNow.dohvatiPodruznice(regijaMikrokredita_tipka.getValue().getIdRegije()));
    }

    // Metoda za promjene ureda pri promjenama podruznica u choiceBoxu

    public void promjenaPodruznice() {

        uredMikrokredita_tipka.getItems().setAll(connectNow.dohvatiUrede(podruznicaMikrokredita_tipka.getValue().getIdPodruznice()));
    }

    // Metoda unesi sa mikrokredit windowsa

    @FXML
    public void unesiAction() {

        if     (brojUgovora_tipka.getText().equals("") ||
                regijaMikrokredita_tipka.getSelectionModel().isEmpty() ||
                podruznicaMikrokredita_tipka.getSelectionModel().isEmpty() ||
                uredMikrokredita_tipka.getSelectionModel().isEmpty() ||
                iznosMK_tipka.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setContentText("Molimo popunite prikazana polja!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {

            Connection connectDataBase = connectNow.getConnection();
            String brojUgovora = brojUgovora_tipka.getText();
            LocalDate datumMK = datumIsplateMikrokredita_tipka.getValue();
            String iznosMK = iznosMK_tipka.getText();
            int IDRegije = regijaMikrokredita_tipka.getValue().getIdRegije();
            int IDPodruznice = podruznicaMikrokredita_tipka.getValue().getIdPodruznice();
            int IDRUreda = uredMikrokredita_tipka.getValue().getIdUreda();

            String querryPolja = "INSERT INTO mikrokrediti (`Broj_Ugovora`, `Datum_Isplate`," +
                    " `Iznos_Mikrokredita`, `ID_Regije`, `ID_Podruznice`, `ID_Uredi`) VALUES ('";

            String querryVrijednosti = brojUgovora + "','" + datumMK + "','" + iznosMK + "','" + IDRegije + "','" +
                    "" + IDPodruznice + "','" + IDRUreda + "')";

            String querry = querryPolja + querryVrijednosti;

            try {
                Statement statement = connectDataBase.createStatement();
                statement.executeUpdate(querry);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potvrda");
                alert.setContentText("Unos mikrokredita je uspjesan!");
                alert.setHeaderText(null);
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upozorenje");
                alert.setContentText("Odabrani ugovor je postojeci!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
    }

    // Metoda ispravi sa mikrokredit windowsa

    @FXML
    public void ispraviAction() {

        if     (brojUgovora_tipka.getText().equals("") ||
                regijaMikrokredita_tipka.getSelectionModel().isEmpty() ||
                podruznicaMikrokredita_tipka.getSelectionModel().isEmpty() ||
                uredMikrokredita_tipka.getSelectionModel().isEmpty() ||
                iznosMK_tipka.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setContentText("Molimo popunite prikazana polja!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {

            Connection connectDataBase = connectNow.getConnection();
            String brojUgovora = brojUgovora_tipka.getText();
            LocalDate datumMK = datumIsplateMikrokredita_tipka.getValue();
            String iznosMK = iznosMK_tipka.getText();
            int IDRegije = regijaMikrokredita_tipka.getValue().getIdRegije();
            int IDPodruznice = podruznicaMikrokredita_tipka.getValue().getIdPodruznice();
            int IDRUreda = uredMikrokredita_tipka.getValue().getIdUreda();

            String querry = "UPDATE mikrokrediti SET `Datum_Isplate` = '" + datumMK + "" +
                    " ', `Iznos_Mikrokredita` = '" + iznosMK + "', `ID_Regije` = '" + IDRegije + "" +
                    "', `id_Podruznice` = '" + IDPodruznice + "', `ID_Uredi` = '" + IDRUreda + "" +
                    "' WHERE (`Broj_Ugovora` = '" + brojUgovora + "');";

            try {
                Statement statement = connectDataBase.createStatement();
                statement.executeUpdate(querry);
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrda");
            alert.setContentText("Izmjena mikrokredita je uspjesna!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    // Metoda obriši sa mikrokredit windowsa

    @FXML
    public void obrisiAction() {

        if     (brojUgovora_tipka.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setContentText("Molimo popunite prikazana polja!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {

            Connection connectDataBase = connectNow.getConnection();
            String brojUgovora = brojUgovora_tipka.getText();
            String querry = "DELETE FROM mikrokrediti WHERE (`Broj_Ugovora` = '" + brojUgovora + "')";

            try {
                Statement statement = connectDataBase.createStatement();
                statement.executeUpdate(querry);
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrda");
            alert.setContentText("Brisanje mikrokredita je uspjesno!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    // Prijelaz sa mikrokredita na pocetnu stranicu

    @FXML
    public void prijelaz4Action() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("pocetna.fxml"));
        Stage window = (Stage) napustiTipka.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
