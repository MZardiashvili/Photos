import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.PicturesDatabaseService;
import services.PicturesFileService;

@WebServlet(name = "DeleteAPictureServlet", urlPatterns = "/deleteAPicture")
public class DeleteAPictureServlet extends HttpServlet {

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    super.doDelete(req, resp);
    PicturesDatabaseService picturesDatabaseService=new PicturesDatabaseService();
    int pictureId = Integer.parseInt(req.getParameter("picture_id"));
    String userExtrenalId = req.getParameter("user_external_id");
    String pictureName=req.getParameter("picture_name");
    try {
      picturesDatabaseService.deletePictureInDatabase(pictureId, userExtrenalId);
      PicturesFileService picturesFileService=new PicturesFileService();
      picturesFileService.deletePicture(pictureName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
