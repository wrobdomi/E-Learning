package controllers;

import database.DatabaseService;
import entities.Answer;
import entities.Question;
import entities.Quizz;
import enums.QuizMenuEnum;
import enums.QuizMenuPicturesEnum;
import enums.QuizOptions;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class PlatformController {

    @FXML
    private BorderPane mainPlatform;

    @FXML
    private Pane mainPane;

    @FXML
    private Label mainTitle;


    private ArrayList<Question> usersQuizQuestion = null;
    private ArrayList<ArrayList<Answer>> questionAnswers = null;
    private int actualQuestion = 0;

    public void showUsersQuizzes(){

        mainPane.getChildren().clear();

        mainTitle.setText("List off your quizzes");

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
        ArrayList<Button> buttons = new ArrayList<>();

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
                    new TranslateTransition(Duration.seconds(4), button1);

            tt.setFromX( 0 );
            tt.setToX( button1.getTranslateX() );
            tt.setToY( button1.getTranslateY() );
            tt.play();

            button1.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            button1.setStyle("-fx-background-color: #ffc40d; -fx-font-family: Arial; -fx-font-size: 25px;" +
                                    "-fx-text-fill: white");
                        }
                    });

            button1.addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            button1.setStyle("-fx-background-color: #1d1d1d; -fx-font-family: Arial; -fx-font-size: 25px;" +
                                    "-fx-text-fill: white");
                        }
                    });

            buttons.add(button1);

            System.out.println(q.getQuizId() + q.getQuizName() + q.getUsername());
        }

        for( Button b : buttons ){

            b.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            mainPane.getChildren().clear();
                            showQuizMenu(b.getText());
                        }
                    });
        }

    }


    public void showQuizMenu(String text) {

        mainTitle.setText("QUIZ: " + text);

        int xPosition = 300;
        int xPositionChange = 400;
        int yPosition = 100;
        int buttonWidth = 300;
        int buttonHeight = 300;

        ArrayList<Button> buttons = new ArrayList<>();

        for(QuizOptions q : QuizOptions.values()){

            QuizMenuEnum qme = new QuizMenuEnum(q);
            QuizMenuPicturesEnum qmpe = new QuizMenuPicturesEnum(q);
            Button button1 = new Button(qme.getOption(),
                                new ImageView(qmpe.getOption()));

            button1.setMinWidth(buttonWidth);
            button1.setMinHeight(buttonHeight);
            button1.setStyle("-fx-background-color: #1d1d1d; -fx-font-family: Arial; -fx-font-size: 25px;" +
                    "-fx-text-fill: white");
            button1.setOpacity(0.8);
            button1.setTranslateX(xPosition);
            button1.setTranslateY(yPosition);

            // Position the text under the image.
            button1.setContentDisplay(ContentDisplay.TOP);

            mainPane.getChildren().add(button1);

            TranslateTransition tt =
                    new TranslateTransition(Duration.seconds(3), button1);

            tt.setFromX( 0 );
            tt.setToX( button1.getTranslateX() );
            tt.setToY( button1.getTranslateY() );
            tt.play();

            xPosition = xPosition + xPositionChange;

            button1.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            button1.setStyle("-fx-background-color: #ffc40d; -fx-font-family: Arial; -fx-font-size: 25px;" +
                                    "-fx-text-fill: white");
                        }
                    });

            button1.addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            button1.setStyle("-fx-background-color: #1d1d1d; -fx-font-family: Arial; -fx-font-size: 25px;" +
                                    "-fx-text-fill: white");
                        }
                    });

            buttons.add(button1);
        }

        for(Button b : buttons){
            b.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            mainPane.getChildren().clear();
                            if(b.getText().equals("Add Question")){
                                addQuestion(text);
                            }
                            else if(b.getText().equals("Start Learning")){
                                startQuiz(text);
                            }

                        }
                    });
        }
    }

    private void startQuiz(String text) {

        actualQuestion = 0;

        int questionWidth = 1200;
        int questionHeight = 200;
        int questionX = 50;
        int questionY = 80;

        int answerWidth = 1200;
        int answerHeight = 50;
        int answerX = 50;
        int answerY = 300;
        int initialY = answerY;
        int answerChange = 70;

        int buttonWidth = 100;
        int buttonHeight = 50;
        int xPosition = 600;
        int yPosition = 600;

        mainTitle.setText("Now learning " + text);

        Stage primaryStage = (Stage) mainPlatform.getScene().getWindow();

        DatabaseService databaseService = DatabaseService.getInstance();
        usersQuizQuestion = databaseService.getUsersQuizQuestions(primaryStage.getTitle(), text);
        questionAnswers = new ArrayList<ArrayList<Answer>>();
        ArrayList<Label> questionsLabels = new ArrayList<>();
        ArrayList<ArrayList<Label>> answersLabels = new ArrayList<ArrayList<Label>>();

        for(int i = 0 ; i < usersQuizQuestion.size() ; i++ ){
            questionAnswers.add(databaseService.getQuestionAnswers(usersQuizQuestion.get(i).getQuestionId()));
            answerY = initialY;

            ArrayList<Label> templabels = new ArrayList<>();

            for(int j = 0 ; j < questionAnswers.get(i).size() ; j++){

                final int correct = questionAnswers.get(i).get(j).getCorrect();
                Label label1 = new Label(" " + questionAnswers.get(i).get(j).getAnswer());
                label1.setMinWidth(answerWidth);
                label1.setMinHeight(answerHeight);
                label1.setMaxWidth(answerWidth);
                label1.setMaxHeight(answerHeight);
                label1.setStyle("-fx-background-color: #3e4444; -fx-font-family: Arial; -fx-font-size: 25px;" +
                        "-fx-text-fill: white");
                label1.setContentDisplay(ContentDisplay.LEFT);
                label1.setAlignment(Pos.CENTER_LEFT);
                label1.setTranslateX(answerX);
                label1.setTranslateY(answerY);
                if(i != 0){
                    label1.setVisible(false);
                }
                label1.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if(correct == 1){
                                    label1.setStyle("-fx-background-color: #82b74b; -fx-font-family: Arial; -fx-font-size: 25px;" +
                                            "-fx-text-fill: white");
                                }
                                else{
                                    label1.setStyle("-fx-background-color: #c94c4c; -fx-font-family: Arial; -fx-font-size: 25px;" +
                                            "-fx-text-fill: white");;
                                }
                            }
                        });
                mainPane.getChildren().add(label1);
                templabels.add(label1);
                answerY = answerY + answerChange;
                System.out.println(questionAnswers.get(i).get(j).getAnswer());
            }

            answersLabels.add(templabels);

            Label label = new Label(usersQuizQuestion.get(i).getQuestion());
            label.setMinWidth(questionWidth);
            label.setMinHeight(questionHeight);
            label.setMaxWidth(questionWidth);
            label.setMaxHeight(questionHeight);
            label.setStyle("-fx-background-color: #3e4444; -fx-font-family: Arial; -fx-font-size: 25px;" +
                    "-fx-text-fill: white");
            // label.setOpacity(0.8);
            label.setContentDisplay(ContentDisplay.CENTER);
            label.setAlignment(Pos.CENTER);
            label.setTranslateX(questionX);
            label.setTranslateY(questionY);
            if(i != 0){
                label.setVisible(false);
            }
            mainPane.getChildren().add(label);
            questionsLabels.add(label);
        }

        Button button1 = new Button("Next");
        button1.setMinWidth(buttonWidth);
        button1.setMinHeight(buttonHeight);
        button1.setStyle("-fx-background-color: #1d1d1d; -fx-font-family: Arial; -fx-font-size: 25px;" +
                "-fx-text-fill: white");
        button1.setOpacity(0.8);
        button1.setTranslateX(xPosition);
        button1.setTranslateY(yPosition);
        mainPane.getChildren().add(button1);

        button1.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(actualQuestion < questionsLabels.size()-1){
                            actualQuestion++;
                            questionsLabels.get(actualQuestion).setVisible(true);
                            for(int i = 0; i < answersLabels.get(actualQuestion-1).size() ; i++){
                                answersLabels.get(actualQuestion-1).get(i).setVisible(false);
                            }
                            for(int i = 0; i < answersLabels.get(actualQuestion).size() ; i++){
                                answersLabels.get(actualQuestion).get(i).setVisible(true);
                            }
                        }
                        else{
                            questionsLabels.get(actualQuestion).setText("You answered all questions.");
                            for(int i = 0; i < answersLabels.get(actualQuestion).size() ; i++){
                                answersLabels.get(actualQuestion).get(i).setVisible(false);
                            }
                        }

                    }
                });

    }



    public void addQuestion(String text) {

        Stage primaryStage = (Stage) mainPlatform.getScene().getWindow();

        String user = primaryStage.getTitle();

        try {
            Parent platformView = FXMLLoader.load(getClass().getResource("../views/newquestion.fxml"));

            Scene scene = new Scene(platformView, 1580, 800);

            primaryStage.setScene(scene);
            primaryStage.setTitle(user + ":" + text);

        } catch (IOException e) {
            System.out.println("Unable to load platform.fxml");
            e.printStackTrace();
        }

    }

    public void addNewQuiz(){

        mainPane.getChildren().clear();

        mainTitle.setText("Add new Quiz");

        int textWidth = 450;
        int textHeight = 50;
        int textX = 450;
        int textY = 250;

        int labelWidth = 450;
        int labelHeight = 100;
        int labelX = 450;
        int labelY = 150;

        int buttonWidth = 100;
        int buttonHeight = 50;
        int xPosition = 625;
        int yPosition = 330;

        TextField textField = new TextField();
        textField.setMinWidth(textWidth);
        textField.setMinHeight(textHeight);
        textField.setMaxWidth(textWidth);
        textField.setMaxHeight(textHeight);
        textField.setTranslateX(textX);
        textField.setTranslateY(textY);
        mainPane.getChildren().add(textField);


        Label label = new Label("Quiz Name:");
        label.setMinWidth(labelWidth);
        label.setMinHeight(labelHeight);
        label.setMaxWidth(labelWidth);
        label.setMaxHeight(labelHeight);
        label.setStyle(" -fx-font-family: Arial; -fx-font-size: 30px;" +
                "-fx-text-fill: white");
        label.setContentDisplay(ContentDisplay.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setTranslateX(labelX);
        label.setTranslateY(labelY);
        mainPane.getChildren().add(label);

        Button button1 = new Button("Add");
        button1.setMinWidth(buttonWidth);
        button1.setMinHeight(buttonHeight);
        button1.setStyle("-fx-background-color: #1d1d1d; -fx-font-family: Arial; -fx-font-size: 25px;" +
                "-fx-text-fill: white");
        button1.setOpacity(0.8);
        button1.setTranslateX(xPosition);
        button1.setTranslateY(yPosition);
        mainPane.getChildren().add(button1);

        button1.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        addQuiz(textField.getText());
                        textField.clear();
                    }
        });

    }

    public void addQuiz(String quizName){

        Stage primaryStage = (Stage) mainPlatform.getScene().getWindow();

        DatabaseService databaseService = DatabaseService.getInstance();
        databaseService.addNewQuiz(quizName, primaryStage.getTitle());

    }


}



