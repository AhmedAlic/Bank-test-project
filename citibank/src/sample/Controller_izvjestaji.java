package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_izvjestaji implements Initializable {

    // Tipke izvjestaji.fxml

    @FXML
    ChoiceBox<Regije> regijaIzvjestaja_tipka;
    @FXML
    ChoiceBox<Podruznice> podruznicaIzvjestaja_tipka;
    @FXML
    ChoiceBox<Uredi> uredIzvjestaja_tipka;
    @FXML
    Button napusti_tipka;
    @FXML
    DatePicker periodOd_tipka;
    @FXML
    DatePicker periodDo_tipka;
    @FXML
    TableView<Mikrokrediti> tabelaIzvjestaja;
    @FXML
    TableColumn<Mikrokrediti,Integer> id_kolona;
    @FXML
    TableColumn<Mikrokrediti,String> ugovor_kolona;
    @FXML
    TableColumn<Mikrokrediti,LocalDate> datum_kolona;
    @FXML
    TableColumn<Mikrokrediti,String> isplata_kolona;
    @FXML
    TableColumn<Mikrokrediti,Integer> regija_kolona;
    @FXML
    TableColumn<Mikrokrediti,Integer> podruznica_kolona;
    @FXML
    TableColumn<Mikrokrediti,Integer> ured_kolona;
    public DataBase connectNow = new DataBase();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        regijaIzvjestaja_tipka.getItems().addAll(connectNow.dohvatiSveRegije());
        podruznicaIzvjestaja_tipka.getItems().addAll(connectNow.dohvatiSvePodruznice());
        uredIzvjestaja_tipka.getItems().addAll(connectNow.dohvatiSveUrede());
    }

    // Metoda za promjene podruznica pri promjenama regija u choiceBoxu

    public void promjenaRegije() {

        podruznicaIzvjestaja_tipka.getItems().setAll(connectNow.dohvatiPodruznice(regijaIzvjestaja_tipka.getValue().getIdRegije()));
    }

    // Metoda za promjene ureda pri promjenama podruznica u choiceBoxu

    public void promjenaPodruznice() {

        uredIzvjestaja_tipka.getItems().setAll(connectNow.dohvatiUrede(podruznicaIzvjestaja_tipka.getValue().getIdPodruznice()));
    }

    // Metoda za kreiranje izvjestaja za isplate mikrokredita za odabrani period

    @FXML
    public void uradiIzvjestaj() {

        if     (regijaIzvjestaja_tipka.getSelectionModel().isEmpty() ||
                podruznicaIzvjestaja_tipka.getSelectionModel().isEmpty() ||
                uredIzvjestaja_tipka.getSelectionModel().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setContentText("Molimo popunite prikazana polja!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {

        LocalDate datumOd = periodOd_tipka.getValue();
        LocalDate datumDo = periodDo_tipka.getValue();
        int IDRegije = regijaIzvjestaja_tipka.getValue().getIdRegije();
        int IDPodruznice = podruznicaIzvjestaja_tipka.getValue().getIdPodruznice();
        int IDUreda = uredIzvjestaja_tipka.getValue().getIdUreda();

        ArrayList<Mikrokrediti> listaMikrokredita = connectNow.dohvatiSveMikrokredite(datumOd, datumDo, IDRegije,
                IDPodruznice, IDUreda);

        id_kolona.setCellValueFactory(new PropertyValueFactory<>("idMikrokredita"));
        ugovor_kolona.setCellValueFactory(new PropertyValueFactory<>("brojUgovora"));
        datum_kolona.setCellValueFactory(new PropertyValueFactory<>("datumIsplate"));
        isplata_kolona.setCellValueFactory(new PropertyValueFactory<>("iznosMikrokredita"));
        regija_kolona.setCellValueFactory(new PropertyValueFactory<>("idRegije"));
        podruznica_kolona.setCellValueFactory(new PropertyValueFactory<>("idPodruznice"));
        ured_kolona.setCellValueFactory(new PropertyValueFactory<>("idUreda"));

        ObservableList<Mikrokrediti> lista = FXCollections.observableArrayList(listaMikrokredita);
        tabelaIzvjestaja.setItems(lista);
        }
    }

    // Prijelaz sa izvjestaja na pocetnu stranicu

    public void prijelaz6Action() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("pocetna.fxml"));
        Stage window = (Stage) napusti_tipka.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
