package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class Registration {
    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextArea result;
    @FXML
    private ImageView error;

    @FXML
    private Button back_button;


    @FXML
    void onclick(ActionEvent event) {
        try {

            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/lito";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "username", "password");

            // create a sql date object so we can use it in our INSERT statement
            // the mysql statement
            String post_request = " insert into users (username, password,email)"
                    + " values (?, ?, ?)";
            String get_request = "SELECT * FROM users";

//        ** Check if are there exist account **  //
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(get_request);

            if (rs.next()) {
                String firstName = rs.getString("username");
                if (firstName.equals(name.getText())) {
                    result.setText("User " + name.getText() + " \n is already registered");
                    Image image = new Image("sample/error.jpg");
                    error.setImage(image);
                    return;
                }


            }
            if (rs.next()) {
                String firstName = rs.getString("username");

                if (!firstName.equals(name.getText())) {
                    //                 create the mysql insert preparedstatement

                    PreparedStatement preparedStmt = conn.prepareStatement(post_request);
                    preparedStmt.setString(1, name.getText());
                    preparedStmt.setString(2, password.getText());
                    preparedStmt.setString(3, email.getText());


                    // execute the preparedstatement

                    preparedStmt.execute();
                    result.setText("Registration was succesfull");
                    Image image = new Image("sample/g_mark.png");
                    error.setImage(image);
                    return;


                }
            }


        } catch (Exception c) {
            System.err.println("Got an exception!");
            System.err.println(c.getMessage());
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("templates/greeting.fxml"));
        primaryStage.setTitle("Hello,I'm LITO");
        primaryStage.setScene(new Scene(root, 685, 400));
        primaryStage.show();
    }


}
