package controllers;

import database.DatabaseService;
import entities.Quizz;
import enums.QuizMenuEnum;
import enums.QuizMenuPicturesEnum;
import enums.QuizOptions;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class PlatformController {

    @FXML
    private BorderPane mainPlatform;

    @FXML
    private Pane mainPane;

    @FXML
    private Label mainTitle;

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
                    new TranslateTransition(Duration.seconds(5), button1);

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

//                            final Rotate rotationTransform = new Rotate(0, 75, 75);
//                            b.getTransforms().add(rotationTransform);
//
//                            final Timeline rotationAnimation = new Timeline();
//                            rotationAnimation.getKeyFrames().add( new KeyFrame(
//                                                    Duration.seconds(1),
//                                                    new KeyValue( rotationTransform.angleProperty(),360 )));
//                            rotationAnimation.play();

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

        }
    }



}
