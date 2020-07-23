import com.google.gson.Gson;
import data.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.PicturesDatabaseService;

@WebServlet(name = "AddPictureUserServlet", urlPatterns = "/addUser")
public class AddPictureUserServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    String data;
    StringBuilder builder = new StringBuilder();
    BufferedReader reader = req.getReader();
    String line;
    while ((line = reader.readLine()) != null) {
      builder.append(line);
    }
    data = builder.toString();
    User user = new Gson().fromJson(data, User.class);
    PicturesDatabaseService notesDatabaseService = new PicturesDatabaseService();
    try {
      notesDatabaseService.savaUserInDatabase(user);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}
