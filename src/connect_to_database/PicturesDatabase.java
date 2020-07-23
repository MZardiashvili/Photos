package connect_to_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PicturesDatabase {

  private Connection getConnection() throws SQLException, ClassNotFoundException {
    String url = "jdbc:postgresql://localhost:5432/images_storage";
    String user = "postgres";
    String password = "postgres";
    Class.forName("org.postgresql.Driver");
    Connection connection = DriverManager.getConnection(url, user, password);
    return connection;
  }

  public ResultSet executeQuery(String query) {
    Statement statement= null;
    try {
      statement = getConnection().createStatement();
      ResultSet resultSet=statement.executeQuery(query);
      return resultSet;
    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  public int executeUpdate(String query) {
    try {
      Statement statement=getConnection().createStatement();
      return  statement.executeUpdate(query);
    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
    }
    return 0;
  }
}
