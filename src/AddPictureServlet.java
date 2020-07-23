import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import data.Picture;
import services.PicturesDatabaseService;

@MultipartConfig
@WebServlet(name = "AddPictureInFileServlet", urlPatterns = "/addImage")
public class AddPictureServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Part part=req.getPart("photo");
    InputStream isFoto = part.getInputStream();
    String imageName=req.getParameter("image_name");
    String imageDate=req.getParameter("image_date");
    int userId= Integer.parseInt(req.getParameter("user_id"));
    OutputStream outStream = new FileOutputStream( new File
      (String.format("C:/Users/User/Documents/apache-tomcat-8.0.9/bin/folderi/%s.jpg", imageName)));
    byte[] buffer = new byte[1024];
    int len;
    while ((len = isFoto.read(buffer)) != -1) {
      outStream.write(buffer, 0, len);
    }
    outStream.flush();
    outStream.close();
    Picture picture=new Picture();
    picture.setPictureName(imageName);
    picture.setPictureDate(imageDate);
    picture.setUserId(userId);
    PicturesDatabaseService picturesDatabaseService=new PicturesDatabaseService();
    picturesDatabaseService.addPicture(picture);
  }
}
