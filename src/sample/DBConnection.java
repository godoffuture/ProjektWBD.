package sample;

import javafx.scene.control.Alert;

import java.sql.*;

public class DBConnection {

    private static Connection conn;

    public static Connection getConnection() {
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl1";
        String DB_USER = "SYSTEM";
        String DB_PASS = "System2019";
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("You are connected to database...");
//            alert.show();
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error to database connection");
            alert.setContentText("Details: "+ exc.getMessage());
            alert.show();
        }
        return conn;
    }
}
