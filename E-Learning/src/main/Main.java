package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent login = FXMLLoader.load(getClass().getResource("../views/login.fxml"));

            Scene scene = new Scene(login);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.setX(50);
            primaryStage.setY(50);
            primaryStage.setMinWidth(1500);
            primaryStage.setMinHeight(850);
            primaryStage.setResizable(false);
            primaryStage.setFullScreen(false);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }

}

