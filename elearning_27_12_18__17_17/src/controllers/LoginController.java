package controllers;

import database.DatabaseService;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
     * loads scene
     */

    private void loadStage(String title){
        Stage primaryStage = (Stage) mainAnchor.getScene().getWindow();

        try {
            Parent platform = FXMLLoader.load(getClass().getResource("../views/platform.fxml"));

            Scene scene = new Scene(platform);

            primaryStage.setScene(scene);
            primaryStage.setTitle(title);

        } catch (IOException e) {
            System.out.println("Unable to load platform.fxml");
            e.printStackTrace();
        }
    }
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
        System.out.println(username.getText());
        System.out.println(password.getText());

        User user = databaseService.checkUser(username.getText(), password.getText());

        if(user != null){ // if user registered
            this.loadStage(user.getUsername());
        }
        else{ // if user not registered show error dialog
            this.signinAlert();
            //this.showErrorDialog();
        }

    }

    public void createUser(ActionEvent event) {
        //adding user to database, else:
        this.signupAlert();
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

    /**
     *  same as method up ^
     */
    public void signinAlert() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error!");
        a.setHeaderText("Wprowadzone dane są nieprawidłowe...");
        a.setContentText("Nazwa użytkownika lub hasło są niepoprawne!");
        a.show();
    }

    public void signupAlert() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error!");
        a.setHeaderText("Wprowadzone dane są nieprawidłowe...");
        a.setContentText("Taki użytkownik już istnieje!");
        a.show();
    }


}
