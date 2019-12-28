package sample;

import java.sql.*;

public class Connector {
    String myUrl = "jdbc:mysql://localhost/lito";
    Connection conn = DriverManager.getConnection(myUrl, "username", "password");
    String select_data = "SELECT * FROM books";
    Statement statement = conn.createStatement();

    ResultSet rs = statement.executeQuery(select_data);
    String delete_data = "Delete  FROM books where title=?";
    String post_request = " insert into users (username, password,email)"
            + " values (?, ?, ?)";
    PreparedStatement preparedStmt = conn.prepareStatement(delete_data);


    public Connector() throws SQLException {
    }
}
