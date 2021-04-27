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

public class Controller_isplate implements Initializable {

    // Tipke isplate.fxml

    @FXML
    Button napustiIsplata_tipka;
    @FXML
    DatePicker pocetniDatum_tipka;
    @FXML
    DatePicker zavrsniDatum_tipka;
    @FXML
    ChoiceBox<Regije> grupisi1_tipka;
    @FXML
    ChoiceBox<Podruznice> grupisi2_tipka;
    @FXML
    TableView<Isplate> isplateMK1_tabela;
    @FXML
    TableColumn<Isplate, Integer> brojMK1_kolona;
    @FXML
    TableColumn<Isplate, Integer> maxMK1_kolona;
    @FXML
    TableColumn<Isplate, Integer> sumaMK1_kolona;
    @FXML
    TableColumn<Isplate, Integer> prosjekMK1_kolona;
    @FXML
    TableView<Isplate> isplateMK2_tabela;
    @FXML
    TableColumn<Isplate, Integer> brojMK2_kolona;
    @FXML
    TableColumn<Isplate, Integer> maxMK2_kolona;
    @FXML
    TableColumn<Isplate, Integer> sumaMK2_kolona;
    @FXML
    TableColumn<Isplate, Integer> prosjekMK2_kolona;
    public DataBase connectNow = new DataBase();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        grupisi1_tipka.getItems().setAll(connectNow.dohvatiSveRegije());
        grupisi2_tipka.getItems().setAll(connectNow.dohvatiSvePodruznice());
    }

    // Metoda za izradu izvjestaja na osnovu grupisanja 1 ili 2

    public void uradiIzvjestaj() {

        if     (grupisi1_tipka.getSelectionModel().isEmpty() ||
                grupisi2_tipka.getSelectionModel().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setContentText("Molimo popunite prikazana polja!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            LocalDate datumOd = pocetniDatum_tipka.getValue();
            LocalDate datumDo = zavrsniDatum_tipka.getValue();
            int IDRegije = grupisi1_tipka.getValue().getIdRegije();
            int IDPodruznice = grupisi2_tipka.getValue().getIdPodruznice();

            ArrayList<Isplate> listaMKRegija = connectNow.dohvatiSveMikrokrediteRegijaIsplata(datumOd, datumDo, IDRegije);
            brojMK1_kolona.setCellValueFactory(new PropertyValueFactory<>("brojIsplacenihMK"));
            maxMK1_kolona.setCellValueFactory(new PropertyValueFactory<>("maxIsplacenihMK"));
            sumaMK1_kolona.setCellValueFactory(new PropertyValueFactory<>("sumaIsplacenihMK"));
            prosjekMK1_kolona.setCellValueFactory(new PropertyValueFactory<>("prosjekIsplacenihMK"));
            ObservableList<Isplate> lista1 = FXCollections.observableArrayList(listaMKRegija);
            isplateMK1_tabela.setItems(lista1);

            ArrayList<Isplate> listaMKPodruznica = connectNow.dohvatiSveMikrokreditePodruznicaIsplata(datumOd, datumDo, IDPodruznice);
            brojMK2_kolona.setCellValueFactory(new PropertyValueFactory<>("brojIsplacenihMK"));
            maxMK2_kolona.setCellValueFactory(new PropertyValueFactory<>("maxIsplacenihMK"));
            sumaMK2_kolona.setCellValueFactory(new PropertyValueFactory<>("sumaIsplacenihMK"));
            prosjekMK2_kolona.setCellValueFactory(new PropertyValueFactory<>("prosjekIsplacenihMK"));
            ObservableList<Isplate> lista2 = FXCollections.observableArrayList(listaMKPodruznica);
            isplateMK2_tabela.setItems(lista2);
        }
    }


    // Prijelaz sa isplata na pocetnu stranicu

    public void prijelaz5Action() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("pocetna.fxml"));
        Stage window = (Stage) napustiIsplata_tipka.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
