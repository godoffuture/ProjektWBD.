package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;


public class ControllerLP {
    private Table_view_model tableview = new Table_view_model();

    @FXML
    private Button login_button;

    @FXML
    private TextField user_name_input,password_input;

    private Connection connection = DBConnection.getConnection();

    public void onLogin(ActionEvent actionEvent ) throws IOException {
        if(tableview.access_check(connection,user_name_input.getText().toString(),password_input.getText().toString())){
            Stage stage = (Stage) login_button.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("database_page.fxml"));
            Parent root = loader.load();

            ControllerDB controllerDB = loader.getController();
            controllerDB.setPracownik_id(user_name_input.getText(),password_input.getText());
            controllerDB.job_check();

            Stage DataBaseSage = new Stage();

            DataBaseSage.setTitle("database page");
            DataBaseSage.setScene(new Scene(root, 900, 500));
            DataBaseSage.show();

            controllerDB.view_bar();

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password detected");
            alert.setContentText("Try again");
            alert.showAndWait();
        }

    }

    public void onForgotPassword(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password detected");
        alert.setContentText("czlonek zarzadu\nUsername: jan\nPassword: jan\nkierowca\nUsername: marek\nPassword: marek");
        alert.showAndWait();
    }


}
