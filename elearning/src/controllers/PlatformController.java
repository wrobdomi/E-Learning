package controllers;

import database.DatabaseService;
import entities.Quizz;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class PlatformController {

    @FXML
    private BorderPane mainPlatform;

    @FXML
    private Pane mainPane;

    public void showUsersQuizzes(){

        int initialX = 100;
        int xPosition = 100;
        int xPositionChange = 250;
        int yPosition = 20;
        int yPositionChange = 200;
        int buttonWidth = 150;
        int buttonHeight = 150;

        Stage primaryStage = (Stage) mainPlatform.getScene().getWindow();
        System.out.println("Title is " + primaryStage.getTitle());

        DatabaseService databaseService = DatabaseService.getInstance();

        ArrayList<Quizz> usersQuizzes = databaseService.getUsersQuizzes(primaryStage.getTitle());

        for(int i = 0 ; i < usersQuizzes.size() ; i++){

            Quizz q = usersQuizzes.get(i);

            if(i % 5 == 0 && i != 0){
                yPosition = yPosition + yPositionChange;
                xPosition = initialX;
            }

            Button button1 = new Button(q.getQuizName());
            button1.setMinWidth(buttonWidth);
            button1.setMinHeight(buttonHeight);
            button1.setStyle("-fx-background-color: #1d1d1d; -fx-font-family: Arial; -fx-font-size: 25px;" +
                    "-fx-text-fill: white");
            button1.setOpacity(0.8);
            button1.setTranslateX(xPosition);
            button1.setTranslateY(yPosition);
            mainPane.getChildren().add(button1);

            xPosition = xPosition + xPositionChange;


            TranslateTransition tt =
                    new TranslateTransition(Duration.seconds(5), button1);

            tt.setFromX( 0 );
            tt.setToX( button1.getTranslateX() );
            tt.setToY( button1.getTranslateY() );
            tt.play();

            System.out.println(q.getQuizId() + q.getQuizName() + q.getUsername());
        }


    }
}
