package services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PicturesFileService {

  public byte[] returnImageToBytes(String imageName) throws IOException {
    InputStream fin = null;
    try {
      fin = new FileInputStream(new File(String
        .format("C:/Users/User/Documents/apache-tomcat-8.0.9/bin/folderi/%s.jpg", imageName)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    int nRead;
    byte[] data = new byte[fin.available()];
    while ((nRead = fin.read(data, 0, data.length)) != -1) {
      byteArrayOutputStream.write(data, 0, nRead);
    }
    return byteArrayOutputStream.toByteArray();
  }

  public void deletePicture(String pictureName){
    Path picturePath= Paths.get(String.format
      ("C:/Users/User/Documents/apache-tomcat-8.0.9/bin/folderi/%s.jpg", pictureName));
    try {
      Files.delete(picturePath);
      System.out.println("Picture has been deleted");
    } catch (IOException e) {
      System.out.println("Picture can't be deleted");
      e.printStackTrace();
    }
  }



  }
