import com.google.gson.Gson;
import data.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.PicturesDatabaseService;

@WebServlet(name = "GetUserServlet", urlPatterns = "/getUser")
public class GetUserServlet  extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    String userName=req.getParameter("userName");
    String userPassword=req.getParameter("userPassword");
    PicturesDatabaseService picturesDatabaseService=new PicturesDatabaseService();
    User user=new User();
    try {
      user=picturesDatabaseService.getUserFromDatabase(userName, userPassword);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    resp.setContentType("application/json");
    String jsonData = new Gson().toJson(user);
    resp.getWriter().write(jsonData);

  }
}
