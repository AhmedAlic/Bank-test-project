package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller_pocetna {

    // Tipke pocetna.fxml

    @FXML
    Button isplateMikrokredita_tipka;
    @FXML
    Button izvjestajiMikrokredita_tipka;
    @FXML
    Button datumiMikrokredita_tipka;
    @FXML
    Button exit_tipka;

    // Prijelaz sa pocetne stranice na mikrokredite

    public void prijelaz1Action() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("mikrokrediti.fxml"));
        Stage window = (Stage) isplateMikrokredita_tipka.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Prijelaz sa pocetne stranice na izvjestaje

    public void prijelaz2Action() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("izvjestaji.fxml"));
        Stage window = (Stage) izvjestajiMikrokredita_tipka.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Prijelaz sa pocetne stranice na isplate

    public void prijelaz3Action() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("isplate.fxml"));
        Stage window = (Stage) isplateMikrokredita_tipka.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Gašenje windowsa

    public void exitAction() {

        Stage stage = (Stage) exit_tipka.getScene().getWindow();
        stage.close();
    }
}

