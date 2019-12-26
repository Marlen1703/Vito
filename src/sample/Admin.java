package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Admin {
    boolean status = true;
    @FXML
    private ListView<?> list;
    @FXML
    private Pane backside;
    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField category;

    @FXML
    private TextField year;

    @FXML
    private TextField riting;
    @FXML
    private Button back;

    @FXML
    void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("templates/greeting.fxml"));
        primaryStage.setTitle("Hello,I'm LITO");
        primaryStage.setScene(new Scene(root, 685, 400));
        primaryStage.show();
    }


    public void initialize() throws SQLException {
        ObservableList books = FXCollections.observableArrayList();
        String myUrl = "jdbc:mysql://localhost/lito";
        Connection conn = DriverManager.getConnection(myUrl, "username", "password");
        String get_request = "SELECT * FROM books";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(get_request);
        while (rs.next()) {
            String book = rs.getString("title");
            books.addAll(book);

        }
        list.setItems(books);


    }

    @FXML
    void remove(ActionEvent event) throws SQLException {
        boolean isMyListNoselected = list.getSelectionModel().isEmpty();
        if (isMyListNoselected == false) {
            try {
                String myUrl = "jdbc:mysql://localhost/lito";
                Connection conn = DriverManager.getConnection(myUrl, "username", "password");
                ObservableList ebook = list.getSelectionModel().getSelectedItems();
                String res = ebook.get(0).toString();
                System.out.println(res);
                String get_request = "Delete  FROM books where title=?";
                PreparedStatement preparedStmt = conn.prepareStatement(get_request);
                preparedStmt.setString(1, res);
                preparedStmt.execute();
                conn.close();
                list.getItems().removeAll(list.getSelectionModel().getSelectedItems());


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You did not select option!");
            alert.showAndWait();

        }

    }

    @FXML
    void include(ActionEvent event) throws SQLException {
        if (status == true) {
            backside.setVisible(true);
            status = false;

        } else {
            if (title.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("The fields are empty");
                alert.showAndWait();
            } else {
                String myDriver = "org.gjt.mm.mysql.Driver";
                String myUrl = "jdbc:mysql://localhost/lito";
                Connection conn = DriverManager.getConnection(myUrl, "username", "password");

                String post_request = " insert into books (title, author,category,year,riting)"
                        + " values (?, ?, ?,?,?)";
                PreparedStatement preparedStmt = conn.prepareStatement(post_request);
                preparedStmt.setString(1, title.getText());
                preparedStmt.setString(2, author.getText());
                preparedStmt.setString(3, category.getText());
                preparedStmt.setString(4, year.getText());
                preparedStmt.setString(5, riting.getText());
                preparedStmt.execute();
                backside.setVisible(false);
                status = true;
            }


        }


    }


}


