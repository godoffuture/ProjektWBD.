package sample;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 * slajd table column propertyvaluefactory w "" powinno byc "PublisherId" zamiast "publisherId"
 */
public class Main extends Application {

    @Override
    public void start(Stage LoginStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login_page.fxml"));
        LoginStage.setTitle("Login page");
        LoginStage.setScene(new Scene(root, 300, 400));
        LoginStage.show();

//        Stage DataBaseSage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("database_page.fxml"));
//        DataBaseSage.setTitle("database page");
//        DataBaseSage.setScene(new Scene(root, 800, 500));
//        DataBaseSage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
