
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.PicturesFileService;

@WebServlet(name = "GetPictureFromFileServlet", urlPatterns = "/getPicture")
public class GetPictureServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    String imageName=req.getParameter("image_name");
    byte[] bytes=new PicturesFileService().returnImageToBytes(imageName);
    resp.getOutputStream().write(bytes);
    resp.getOutputStream().flush();
    resp.getOutputStream().close();
  }
}
