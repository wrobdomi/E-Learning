package controllers;

import database.DatabaseService;
import entities.Quizz;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PlatformController {

    @FXML
    private BorderPane mainPlatform;

    public void showUsersQuizzes(){
        Stage primaryStage = (Stage) mainPlatform.getScene().getWindow();
        System.out.println("Title is " + primaryStage.getTitle());

        DatabaseService databaseService = DatabaseService.getInstance();

        ArrayList<Quizz> usersQuizzes = databaseService.getUsersQuizzes(primaryStage.getTitle());
        for(Quizz q : usersQuizzes){
            System.out.println(q.getQuizId() + q.getQuizName() + q.getUsername());
        }
    }




}
