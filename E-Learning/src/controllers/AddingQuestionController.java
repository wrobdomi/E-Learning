package controllers;

import database.DatabaseService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class AddingQuestionController {

    @FXML
    private TextField question;

    @FXML
    private TextField answerA;

    @FXML
    private CheckBox correctA;

    @FXML
    private TextField answerB;

    @FXML
    private CheckBox correctB;

    @FXML
    private TextField answerC;

    @FXML
    private CheckBox correctC;

    @FXML
    private TextField answerD;

    @FXML
    private CheckBox correctD;

    @FXML
    private BorderPane mainPlatform;


    public void addQuestion(){

        System.out.println("Quesetion is " + question.getText());
        System.out.println("A is " + answerA.getText());
        System.out.println("A checked is " + correctA.isSelected());
        System.out.println("B is " + answerB.getText());
        System.out.println("B checked is " + correctB.isSelected());
        System.out.println("C is " + answerC.getText());
        System.out.println("C checked is " + correctC.isSelected());
        System.out.println("D is " + answerD.getText());
        System.out.println("D checked is " + correctD.isSelected());

        Stage primaryStage = (Stage) mainPlatform.getScene().getWindow();
        String title = primaryStage.getTitle();

        String[] arr = title.split(":", -1);
        System.out.println(arr[0]);
        System.out.println(arr[1]);

        DatabaseService databaseService = DatabaseService.getInstance();

        int quizId = databaseService.getUsersQuizId(arr[0], arr[1]);
        boolean added = databaseService.insertQuestion(quizId, question.getText());
        int questionId = databaseService.getQuizQuestionId(question.getText(), quizId);

        int isCorrectA = 0;
        int isCorrectB = 0;
        int isCorrectC = 0;
        int isCorrectD = 0;

        if(correctA.isSelected()) {
            isCorrectA = 1;
        }
        if(correctB.isSelected()) {
            isCorrectB = 1;
        }
        if(correctC.isSelected()) {
            isCorrectC = 1;
        }
        if(correctD.isSelected()) {
            isCorrectD = 1;
        }

        if(!answerA.getText().equals("")){
            databaseService.addQuestionsAnswer(questionId, answerA.getText(), isCorrectA);
        }
        if(!answerB.getText().equals("")){
            databaseService.addQuestionsAnswer(questionId, answerD.getText(), isCorrectB);
        }
        if(!answerC.getText().equals("")){
            databaseService.addQuestionsAnswer(questionId, answerC.getText(), isCorrectC);
        }
        if(!answerD.getText().equals("")){
            databaseService.addQuestionsAnswer(questionId, answerD.getText(), isCorrectD);
        }

        answerA.clear();
        answerB.clear();
        answerC.clear();
        answerD.clear();
        question.clear();
        correctA.setSelected(false);
        correctB.setSelected(false);
        correctC.setSelected(false);
        correctD.setSelected(false);

        this.showSuccessfullyAddedQuestion();

    }

    public void goBackToMyQuizzes(){

        Stage primaryStage = (Stage) mainPlatform.getScene().getWindow();
        String title = primaryStage.getTitle();

        String[] arr = title.split(":", -1);

        try {
            Parent platform = FXMLLoader.load(getClass().getResource("../views/platform.fxml"));

            Scene scene = new Scene(platform);

            primaryStage.setScene(scene);
            primaryStage.setTitle(arr[0]);

        } catch (IOException e) {
            System.out.println("Unable to load platform.fxml");
            e.printStackTrace();
        }

    }



    public void showSuccessfullyAddedQuestion(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPlatform.getScene().getWindow());
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../views/addedquestion.fxml"));
            dialog.getDialogPane().setContent(root);
        }catch(IOException e){
            System.out.println("Cant load the dialog.");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.showAndWait();
    }



}
