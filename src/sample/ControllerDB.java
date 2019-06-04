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

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerDB implements Initializable {


    @FXML
    private TableView<Table_view_model> myTable;
////////////////////////////////////////////////////////////////

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_KONTAKT;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_IMIE;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_ULICA;

    @FXML
    private TableColumn<Integer, Table_view_model> tableColumn_ID_PRACOWNIK;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_KOD_POCZTOWY;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_STANOWISKO;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_MIASTO;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_NAZWISKO;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_KONTO_BANKOWE;
///////////////////////////////////////////////////////////////////////

    @FXML
    private TableColumn<Date, Table_view_model> tableColumn_DATA_PRZEGLADU;

    @FXML
    private TableColumn<Integer, Table_view_model> tableColumn_POJEMNOSC;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_NUMER_BOCZNY;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_TYP;

    @FXML
    private TableColumn<Integer, Table_view_model> tableColumn_ID_POJAZD;

    @FXML
    private TableColumn<Date, Table_view_model> tableColumn_ROK_PRODUKCJI;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_PRODUCENT;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_MODEL;
///////////////////////////////////////////////////////////////////////////


    @FXML
    private TableColumn<Integer, Table_view_model> tableColumn_GPS_Y;

    @FXML
    private TableColumn<Integer, Table_view_model> tableColumn_GPS_X;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_STREFA;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_TYP_STACJI;

    @FXML
    private TableColumn<Integer, Table_view_model> tableColumn_ID_STACJA;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_MODEL_STACJI;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_NAZWA;

    @FXML
    private TableColumn<String, Table_view_model> tableColumn_BILETOMAT;


    @FXML
    private Button add_button;

    @FXML
    private Button edit_button;

    @FXML
    private Button delete_button;

    @FXML
    public Label job_title_lbl;

    @FXML
    public Label name_lbl;

    @FXML
    public Label textValue;

    @FXML
    private Button log_out_click;

    @FXML
    private TextField filter_value;

    private Table_view_model tableview = new Table_view_model();

    ObservableList<Table_view_model> list_tableview = FXCollections.observableArrayList();

    private Connection connection;

    private String username,password,new_username,new_password;

    ObservableList<String> column_pracownicy_list = FXCollections.observableArrayList(
            "IMIE",
            "NAZWISKO",
            "STANOWISKO",
            "MIASTO"
    );
    ObservableList<String> column_stacja_list = FXCollections.observableArrayList(
            "NAZWA",
            "TYP_STACJI",
            "BILETOMAT"
    );
    ObservableList<String> column_pojazd_list = FXCollections.observableArrayList(
            "PRODUCENT",
            "NUMER_BOCZNY",
            "POJEMNOSC",
            "MODEL"
    );

    ObservableList<String> column_nazwa_lini = FXCollections.observableArrayList(
            "N1",
            "1A",
            "1B",
            "2"
    );

    ObservableList<String> high_table_list = FXCollections.observableArrayList(
            "PRACOWNICY",
            "STACJA",
            "POJAZD",
            "STACJA NA LINII"

    );

    ObservableList<String> low_table_list = FXCollections.observableArrayList(
            "STACJA",
            "STACJA NA LINII"
    );

    @FXML
    private ChoiceBox column_box;


    @FXML
    private ChoiceBox open_table_box;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        connection = DBConnection.getConnection();
    }

    public void table_pojazd(){

        isPracownicyVisible(false);
        isPojazdVisible(true);
        isStacjaVisible(false);

        list_tableview = tableview.getAllFromPojazd(connection);

        tableColumn_ID_POJAZD.setCellValueFactory(new PropertyValueFactory<>("ID_POJAZD"));

        tableColumn_ROK_PRODUKCJI.setCellValueFactory(new PropertyValueFactory<>("ROK_PRODUKCJI"));

        tableColumn_PRODUCENT.setCellValueFactory(new PropertyValueFactory<>("PRODUCENT"));

        tableColumn_DATA_PRZEGLADU.setCellValueFactory(new PropertyValueFactory<>("DATA_PRZEGLADU"));

        tableColumn_POJEMNOSC.setCellValueFactory(new PropertyValueFactory<>("POJEMNOSC"));

        tableColumn_NUMER_BOCZNY.setCellValueFactory(new PropertyValueFactory<>("NUMER_BOCZNY"));

        tableColumn_TYP.setCellValueFactory(new PropertyValueFactory<>("TYP"));

        tableColumn_MODEL.setCellValueFactory(new PropertyValueFactory<>("MODEL"));

        myTable.setItems(list_tableview);
    }

    public void job_check(){
        if(tableview.getJob_title(connection,username,password).equals("czlonek zarzadu")){
            open_table_box.setItems(high_table_list);isButtonsVisible(true);
        } else {
            open_table_box.setItems(low_table_list);
            isButtonsVisible(false);
            isPojazdVisible(false);
            isPracownicyVisible(false);
            isStacjaVisible(true);}
    }

    private void table_pracownicy(){

        isPojazdVisible(false);
        isPracownicyVisible(true);
        isStacjaVisible(false);

        list_tableview = tableview.getAllFromPracownicy(connection);

        tableColumn_ID_PRACOWNIK.setCellValueFactory(new PropertyValueFactory<>("ID_PRACOWNIK"));

        tableColumn_IMIE.setCellValueFactory(new PropertyValueFactory<>("IMIE"));

        tableColumn_NAZWISKO.setCellValueFactory(new PropertyValueFactory<>("NAZWISKO"));

        tableColumn_STANOWISKO.setCellValueFactory(new PropertyValueFactory<>("STANOWISKO"));

        tableColumn_MIASTO.setCellValueFactory(new PropertyValueFactory<>("MIASTO"));

        tableColumn_ULICA.setCellValueFactory(new PropertyValueFactory<>("ULICA"));

        tableColumn_KONTAKT.setCellValueFactory(new PropertyValueFactory<>("NUMER"));

        tableColumn_KONTO_BANKOWE.setCellValueFactory(new PropertyValueFactory<>("KONTO_BANKOWE"));

        tableColumn_KOD_POCZTOWY.setCellValueFactory(new PropertyValueFactory<>("KOD_POCZTOWY"));

        myTable.setItems(list_tableview);
    }

    private void table_stacja(){
        isPojazdVisible(false);
        isPracownicyVisible(false);
        isStacjaVisible(true);

        list_tableview = tableview.getAllFromStacja(connection);

        tableColumn_ID_STACJA.setCellValueFactory(new PropertyValueFactory<>("ID_STACJA"));

        tableColumn_NAZWA.setCellValueFactory(new PropertyValueFactory<>("NAZWA"));

        tableColumn_GPS_X.setCellValueFactory(new PropertyValueFactory<>("GPS_X"));

        tableColumn_GPS_Y.setCellValueFactory(new PropertyValueFactory<>("GPS_Y"));

        tableColumn_TYP_STACJI.setCellValueFactory(new PropertyValueFactory<>("TYP_STACJI"));

        tableColumn_MODEL_STACJI.setCellValueFactory(new PropertyValueFactory<>("MODEL_STACJI"));

        tableColumn_BILETOMAT.setCellValueFactory(new PropertyValueFactory<>("BILETOMAT"));

        tableColumn_STREFA.setCellValueFactory(new PropertyValueFactory<>("STREFA"));

        myTable.setItems(list_tableview);
    }


    private void isButtonsVisible(boolean i){
        edit_button.setVisible(i);
        add_button.setVisible(i);
        delete_button.setVisible(i);
    }

    private void isPracownicyVisible(boolean i){
        tableColumn_ID_PRACOWNIK.setVisible(i);

        tableColumn_IMIE.setVisible(i);

        tableColumn_NAZWISKO.setVisible(i);

        tableColumn_STANOWISKO.setVisible(i);

        tableColumn_MIASTO.setVisible(i);

        tableColumn_ULICA.setVisible(i);

        tableColumn_KONTAKT.setVisible(i);

        tableColumn_KONTO_BANKOWE.setVisible(i);

        tableColumn_KOD_POCZTOWY.setVisible(i);
    }

    private void isPojazdVisible(boolean i){
        tableColumn_ID_POJAZD.setVisible(i);

        tableColumn_ROK_PRODUKCJI.setVisible(i);

        tableColumn_PRODUCENT.setVisible(i);

        tableColumn_DATA_PRZEGLADU.setVisible(i);

        tableColumn_POJEMNOSC.setVisible(i);

        tableColumn_NUMER_BOCZNY.setVisible(i);

        tableColumn_TYP.setVisible(i);

        tableColumn_MODEL.setVisible(i);
    }

    private void isStacjaVisible(boolean i){
        tableColumn_ID_STACJA.setVisible(i);

        tableColumn_NAZWA.setVisible(i);

        tableColumn_GPS_X.setVisible(i);

        tableColumn_GPS_Y.setVisible(i);

        tableColumn_TYP_STACJI.setVisible(i);

        tableColumn_MODEL_STACJI.setVisible(i);

        tableColumn_BILETOMAT.setVisible(i);

        tableColumn_STREFA.setVisible(i);
    }

    public void onFilterby(){
        list_tableview = tableview.getFilteredBy(connection,open_table_box.getSelectionModel().getSelectedItem().toString(),column_box.getSelectionModel().getSelectedItem().toString(),filter_value.getText());

        switch(open_table_box.getSelectionModel().getSelectedItem().toString()) {
            case "PRACOWNICY":
                isPracownicyVisible(true);
                isPojazdVisible(false);
                isStacjaVisible(false);
                break;
            case "POJAZD":
                isPracownicyVisible(false);
                isPojazdVisible(true);
                isStacjaVisible(false);
                break;
            case "STACJA":
                isPracownicyVisible(false);
                isPojazdVisible(false);
                isStacjaVisible(true);
                break;
            case "STACJA NA LINII":
                isPracownicyVisible(false);
                isPojazdVisible(false);
                isStacjaVisible(true);
                break;
            default:
        }
        myTable.setItems(list_tableview);

        filter_value.setText("");
    }

    public void view_profile(){
        tableview.view_profile(connection,username,password);

    }

    public void view_bar(){
        tableview.profile_bar(connection, username, password);

        name_lbl.setText(tableview.getIMIE() + " " + tableview.getNAZWISKO());
        job_title_lbl.setText(tableview.getSTANOWISKO());
    }

    public void setPracownik_id(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void onChangePassword(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("change password Dialog");
        dialog.setContentText("Please enter your new password:");
        Optional<String> result = dialog.showAndWait();
        new_password = result.get();

        tableview.change_password(connection,username,password,new_password);
        password = new_password;
    }

    public void onChangeUsername(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("change username Dialog");
        dialog.setContentText("Please enter your new username:");
        Optional<String> result = dialog.showAndWait();
        new_username = result.get();

        tableview.change_username(connection,username,password,new_username);
        tableview.profile_bar(connection,username,new_username);
        username = new_username;
    }

    public void onAdd() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add_page.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();

        stage.setTitle("Add page");
        stage.setScene(new Scene(root, 400, 450));
        stage.show();

        table_pracownicy();
    }

    public void onEdit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_page.fxml"));
        Parent root = loader.load();

        try {
            ControllerEdit controllerEdit = loader.getController();
            controllerEdit.set_values(myTable.getSelectionModel().getSelectedItem());

            Stage stage = new Stage();

            stage.setTitle("Add page");
            stage.setScene(new Scene(root, 400, 450));
            stage.show();
        }catch(Exception a){}
        table_pracownicy();
    }

    public void onDelete(){
        tableview.delete_pracownik(connection, myTable.getSelectionModel().getSelectedItem().getID_PRACOWNIK());
        table_pracownicy();
    }

    public void onLogOut() throws IOException {
        Stage stage = (Stage) log_out_click.getScene().getWindow();
        stage.close();
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("login_page.fxml"));
        st.setTitle("Login page");
        st.setScene(new Scene(root, 300, 400));
        st.show();
    }

    public void onOpenTable(){

        switch(open_table_box.getSelectionModel().getSelectedItem().toString()) {
            case "PRACOWNICY":
                textValue.setVisible(true);
                filter_value.setVisible(true);
                table_pracownicy();
                isButtonsVisible(true);
                break;

            case "POJAZD":
                textValue.setVisible(true);
                filter_value.setVisible(true);
                table_pojazd();
                isButtonsVisible(false);
                break;

            case "STACJA":
                textValue.setVisible(true);
                filter_value.setVisible(true);
                table_stacja();
                isButtonsVisible(false);
                break;

            case "STACJA NA LINII":
                textValue.setVisible(false);
                filter_value.setVisible(false);
                table_stacja();
                isButtonsVisible(false);

                break;

            default:
                // code block
        }

    }

    public void onDragChoiceBox(){
        switch(open_table_box.getSelectionModel().getSelectedItem().toString()) {
            case "PRACOWNICY":
                column_box.setItems(column_pracownicy_list);
                break;
            case "POJAZD":
                column_box.setItems(column_pojazd_list);
                break;
            case "STACJA":
                column_box.setItems(column_stacja_list);
                break;
            case "STACJA NA LINII":
                column_box.setItems(column_nazwa_lini);
                break;
            default:
        }

    }
}
