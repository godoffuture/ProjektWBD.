package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.Connection;


public class ControllerAdd {

    @FXML
    private TextField bank;

    @FXML
    private TextField password;

    @FXML
    private TextField town;

    @FXML
    private TextField surname;

    @FXML
    private TextField street;

    @FXML
    private TextField contact;

    @FXML
    private TextField name;

    @FXML
    private TextField job;

    @FXML
    private TextField kodpocz;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
            private Button add_button;

    Table_view_model tableview = new Table_view_model();

    public void onClicked(){
        Connection connection = DBConnection.getConnection();
        tableview.insert_pracownik(connection,name.getText(),surname.getText(),job.getText(),
                town.getText(),street.getText(),contact.getText(),bank.getText(),
                kodpocz.getText(),username.getText(),password.getText());
        Stage stage = (Stage) add_button.getScene().getWindow();
        stage.close();
    }
}
