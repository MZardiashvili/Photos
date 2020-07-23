import data.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.PicturesDatabaseService;

@WebServlet(name = "SearchUserInDatabaseServlet", urlPatterns = "/searchUser")
public class SearchUserInDatabaseServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    String userName = req.getParameter("userName");
    String userPassword = req.getParameter("userPassword");
    User user = new User();
    user.setUserName(userName);
    user.setUserPassword(userPassword);
    PicturesDatabaseService picturesDatabaseService = new PicturesDatabaseService();
    Boolean aBoolean = null;

    try {
      aBoolean = picturesDatabaseService.searchUserInDatabase(user);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    String bool = String.valueOf(aBoolean);
    resp.getWriter().write(bool);
  }
}
