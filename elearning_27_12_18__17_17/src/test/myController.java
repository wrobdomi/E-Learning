package test;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class myController implements Initializable {
    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void showFxml(MouseEvent event) {
        load("test");
    }

    @FXML
    public void showFxml2(MouseEvent event) {
        load("test2");
    }

    private void load(String name) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(name+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(root);
    }



}
