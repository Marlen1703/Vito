package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Controller implements Initializable {
    @FXML
    private Button quit;
    @FXML
    private Pane en_content;


    @FXML
    private TableView<Books> tbl;

    @FXML
    private TableColumn<Books, String> title;

    @FXML
    private TableColumn<Books, String> author;

    @FXML
    private TableColumn<Books, String> category;

    @FXML
    private TableColumn<Books, String> year;

    @FXML
    private TableColumn<Books, String> riting;

    @FXML
    private TextField search;
    @FXML
    private ChoiceBox<String> choicebox;
    @FXML
    private Pane ru_content;
    @FXML
    private TableView<Books> tbl1;

    @FXML
    private TableColumn<?, ?> title1;

    @FXML
    private TableColumn<?, ?> author1;

    @FXML
    private TableColumn<?, ?> category1;

    @FXML
    private TableColumn<?, ?> year1;

    @FXML
    private TableColumn<?, ?> riting1;


    @FXML
    ObservableList<Books> oblist = FXCollections.observableArrayList();
    ObservableList<Books> oblist_2 = FXCollections.observableArrayList();
    FilteredList filteredList = new FilteredList(oblist, e -> true);


    public void initialize(URL url, ResourceBundle rb) {
        try {
            Books books;
            Books books1;
            String myUrl = "jdbc:mysql://localhost/lito";
            Connection conn = DriverManager.getConnection(myUrl, "username", "password");
            String get_request = "SELECT * FROM books";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(get_request);
            choicebox.getItems().add("EN");
            choicebox.getItems().add("RU");
            choicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    String choice = choicebox.getItems().get((Integer) number2);
                    if (choice.equals("EN")) {
                        en_content.setVisible(true);
                        ru_content.setVisible(false);
                    }
                    if (choice.equals("RU")) {
                        en_content.setVisible(false);
                        ru_content.setVisible(true);
                    }

                }
            });

            while (rs.next()) {
                books = new Books(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

                oblist.addAll(books);

            }
            get_request = "SELECT * FROM ru_books";
            ResultSet rs_ru = statement.executeQuery(get_request);

            while (rs_ru.next()) {

                books1 = new Books(rs_ru.getString(2), rs_ru.getString(3), rs_ru.getString(4), rs_ru.getString(5), rs_ru.getString(6));
                oblist_2.addAll(books1);


            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        riting.setCellValueFactory(new PropertyValueFactory<>("riting"));
        tbl.setItems(oblist);
        title1.setCellValueFactory(new PropertyValueFactory<>("title"));
        author1.setCellValueFactory(new PropertyValueFactory<>("author"));
        category1.setCellValueFactory(new PropertyValueFactory<>("category"));
        year1.setCellValueFactory(new PropertyValueFactory<>("year"));
        riting1.setCellValueFactory(new PropertyValueFactory<>("riting"));
        tbl1.setItems(oblist_2);


    }

    @FXML
    void quit(ActionEvent event) throws IOException {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("templates/greeting.fxml"));
        primaryStage.setTitle("Hello,I'm LITO");
        primaryStage.setScene(new Scene(root, 685, 400));
        primaryStage.show();

    }


    @FXML
    void find(KeyEvent event) {
        search.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredList.setPredicate((Predicate<? super Books>) (Books books) -> {
                if (newvalue.isEmpty() || newvalue == null) {
                    return true;

                } else if (books.getAuthor().contains(newvalue)) {
                    return true;
                } else if (books.getTitle().contains(newvalue)) {
                    return true;
                } else if (books.getAuthor().toLowerCase().contains(newvalue)) {
                    return true;
                } else if (books.getRiting().contains(newvalue)) {
                    return true;
                } else if (books.getCategory().contains(newvalue)) {
                    return true;
                } else if (books.getCategory().toLowerCase().contains(newvalue)) {
                    return true;
                } else if (books.getTitle().toLowerCase().contains(newvalue)) {
                    return true;
                } else if (books.getYear().contains(newvalue)) {
                    return true;
                }
                return false;
            });

        });
        SortedList sort = new SortedList(filteredList);
        sort.comparatorProperty().bind(tbl.comparatorProperty());
        tbl.setItems(sort);
    }


}

