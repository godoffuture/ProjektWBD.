package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class ControllerEdit implements Initializable {
    @FXML
    private Label contact_lbl;

    @FXML
    private Label password_lbl;

    @FXML
    private Label job_lbl;

    @FXML
    private TextField town;

    @FXML
    private Label bank_lbl;

    @FXML
    private Button add_button;

    @FXML
    private TextField kodpocz;

    @FXML
    private Label town_lbl;

    @FXML
    private Label surname_lbl;

    @FXML
    private TextField bank;

    @FXML
    private TextField password;

    @FXML
    private Label email_lbl;

    @FXML
    private Label username_lbl;

    @FXML
    private TextField surname;

    @FXML
    private TextField street;

    @FXML
    private TextField contact;

    @FXML
    private Label name_lbl;

    @FXML
    private Label kodpocz_lbl;

    @FXML
    private TextField name;

    @FXML
    private TextField job;

    @FXML
    private Label street_lbl;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    Connection connection;

    Table_view_model tableview;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = DBConnection.getConnection();

    }

    public void apply_edit(){
        int id = tableview.getID_PRACOWNIK();
        String IMIE = name.getText().isEmpty() ? tableview.getIMIE() : name.getText();
        String NAZWISKO = surname.getText().isEmpty() ? tableview.getNAZWISKO() : surname.getText();
        String STANOWISKO = job.getText().isEmpty() ? tableview.getSTANOWISKO() : job.getText();
        String MIASTO = town.getText().isEmpty() ? tableview.getMIASTO() : town.getText();
        String ULICA = street.getText().isEmpty() ? tableview.getULICA() : street.getText();
        String KONTAKT = contact.getText().isEmpty() ? tableview.getNUMER() : contact.getText();
        String KONTO_BANKOWE = bank.getText().isEmpty() ? tableview.getKONTO_BANKOWE() : bank.getText();
        String KOD_POCZTOWY = kodpocz.getText().isEmpty() ? tableview.getKOD_POCZTOWY() : bank.getText();

        tableview.edit_pracownik(connection,id,IMIE, NAZWISKO, STANOWISKO, MIASTO, ULICA, KONTAKT,
         KONTO_BANKOWE, KOD_POCZTOWY);

        Stage stage = (Stage) add_button.getScene().getWindow();
        stage.close();
    }

    public void set_values(Table_view_model tableview){
        this.tableview = tableview;
        name_lbl.setText(tableview.getIMIE());
                surname_lbl.setText(tableview.getNAZWISKO());
        job_lbl.setText(tableview.getSTANOWISKO());
        town_lbl.setText(tableview.getMIASTO());
                street_lbl.setText(tableview.getULICA());
        contact_lbl.setText(tableview.getNUMER());
        bank_lbl.setText(tableview.getKONTO_BANKOWE());
                kodpocz_lbl.setText(tableview.getKOD_POCZTOWY());

    }
}
