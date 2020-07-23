import com.google.gson.Gson;
import data.Picture;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.PicturesDatabaseService;

@WebServlet(name = "GetPictureInfoServlet", urlPatterns = "/getAllPictures")
public class GetAllPicturesServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    PicturesDatabaseService picturesDatabaseService=new PicturesDatabaseService();
    String userExternalId=req.getParameter("userExternalId");
    ArrayList<Picture> pictures=new ArrayList<>();
    try {
      pictures=picturesDatabaseService.getPictures(userExternalId);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    resp.setContentType("application/json");
    String jsonData=new Gson().toJson(pictures);
    resp.getWriter().write(jsonData);
  }

}
