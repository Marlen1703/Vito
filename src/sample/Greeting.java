package sample;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Greeting {
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label text_new;
    @FXML
    private Button button_yes;

    @FXML
    private Button button_no;

    public void initialize(){
        TranslateTransition transition=new TranslateTransition();
        transition.setDuration((Duration.seconds(10)));
        transition.setToX(200);
        transition.setToY(0);
        transition.setAutoReverse(true);
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setNode(rectangle);
        transition.play();
//
        TranslateTransition transition_1=new TranslateTransition();
        transition_1.setDuration((Duration.seconds(10)));
        transition_1.setToX(200);
        transition_1.setToY(0);
        transition_1.setAutoReverse(true);
        transition_1.setCycleCount(Animation.INDEFINITE);
        transition_1.setNode(text_new);
        transition_1.play();
//
        TranslateTransition transition_2=new TranslateTransition();
        transition_2.setDuration((Duration.seconds(10)));
        transition_2.setToX(200);
        transition_2.setToY(0);
        transition_2.setAutoReverse(true);
        transition_2.setCycleCount(Animation.INDEFINITE);
        transition_2.setNode(button_yes);
        transition_2.play();
//

    }
    @FXML
    void fnc_yes(ActionEvent event) throws IOException {

        Stage stage = (Stage) button_yes.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader registration_xml = new FXMLLoader(getClass().getResource("templates/registration.fxml"));
            Parent root1 = (Parent) registration_xml.load();
            Stage stage_1 = new Stage();
            stage_1.setScene(new Scene(root1));
            stage_1.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void fnc_no(ActionEvent event) {
        Stage stage = (Stage) button_no.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader registration_xml = new FXMLLoader(getClass().getResource("templates/login.fxml"));
            Parent root1 = (Parent) registration_xml.load();
            Stage stage_1 = new Stage();
            stage_1.setScene(new Scene(root1));
            stage_1.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

