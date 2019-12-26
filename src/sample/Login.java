package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Login {
    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;
    @FXML
    private ImageView error;
    @FXML
    private Button log_in;
    @FXML
    private Label result;


    @FXML
    private Button back;



    @FXML
    void log_in(ActionEvent event) throws ClassNotFoundException {
        try {
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost/lito";
        Class.forName(myDriver);

            Connection conn = DriverManager.getConnection(myUrl, "username", "password");
            String get_request = "SELECT * FROM users";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(get_request);
            if(userName.getText().equals("admin")){
                if(password.getText().equals("admin")){
                    Stage stage = (Stage) log_in.getScene().getWindow();
                    stage.close();
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("templates/admin.fxml"));
                    primaryStage.setTitle("Hello,I'm LITO");
                    primaryStage.setScene(new Scene(root, 685, 400));
                    primaryStage.show();

                }
            }
            while (rs.next()) {
                String firstName = rs.getString("username");
                String parole=rs.getString("password");
                if(firstName.equals(userName.getText())){
                    if(parole.equals(password.getText())){
                        Stage stage = (Stage) log_in.getScene().getWindow();
                        stage.close();
                        Stage primaryStage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("templates/main_page.fxml"));
                        primaryStage.setTitle("Hello,I'm LITO");
                        primaryStage.setScene(new Scene(root, 685, 400));
                        primaryStage.show();

                    }

                }
                else {
                    Image image=new Image("sample/error.jpg");
                    error.setImage(image);
                    result.setText("Invalid username or password");
                }




            }













        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    void back(ActionEvent event) throws IOException {
        Stage stage = (Stage)back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("templates/greeting.fxml"));
        primaryStage.setTitle("Hello,I'm LITO");
        primaryStage.setScene(new Scene(root, 685, 400));
        primaryStage.show();
    }




}
