package controllers;

import database.DatabaseService;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private AnchorPane mainAnchor;


    /**
     * tries to connect to db using credentials provided by user
     * invoked after 'submit' was clicked
     */
    @FXML
    public void authenticateUser(){

        System.out.println("Authenticating!");

        // get instance of database service
        DatabaseService databaseService = DatabaseService.getInstance();

        // check if user is registered
        User user = databaseService.checkUser(username.getText(), password.getText());

        if(user != null){ // if user registered

            Stage primaryStage = (Stage) mainAnchor.getScene().getWindow();

            try {
                Parent platformView = FXMLLoader.load(getClass().getResource("../views/platform.fxml"));

                Scene scene = new Scene(platformView);

                primaryStage.setScene(scene);
                primaryStage.setTitle(user.getUsername());

            } catch (IOException e) {
                System.out.println("Unable to load platform.fxml");
                e.printStackTrace();
            }


        }
        else{ // if user not registered show error dialog
            this.showErrorDialog();
        }

    }

    /**
     * checks if already exists then creates new user
     * not functioning properly yet
     */
    @FXML
    public void createUser(){
        System.out.println("Creating new user...");
        DatabaseService databaseService = DatabaseService.getInstance();

        // check if user is registered
        boolean exists = databaseService.checkUser(username.getText());

        System.out.println("checking if already exists...");
        if (exists == false){
            System.out.println("adding user to database");
            databaseService.addUser(username.getText(), password.getText());
        }
        else{
            System.out.println("FailDialogController: user exists!");
            this.fail2();
            }
    }
    public void fail2(){
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("Warning!");
        a.setHeaderText("Problem z logowaniem");
        a.setContentText("user takowy juz ma swoje miejsce w tej bazie");
        a.show();


    }

    /**
     * shows dialog pane when connection to db failed
     * after 4 attempts
     */
    public void showErrorDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainAnchor.getScene().getWindow());
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../views/loginfaildialog.fxml"));
            dialog.getDialogPane().setContent(root);
        }catch(IOException e){
            System.out.println("Cant load the dialog.");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        dialog.showAndWait();
    }




}
