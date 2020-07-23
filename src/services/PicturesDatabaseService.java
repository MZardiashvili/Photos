package services;

import connect_to_database.PicturesDatabase;
import data.Picture;
import data.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class PicturesDatabaseService {
  PicturesDatabase picturesDatabase=new PicturesDatabase();

  public void addPicture(Picture picture){
    int result= picturesDatabase.executeUpdate(String.format("insert into images (name, date, user_id) values('%s', '%s', '%s')",
      picture.getPictureName(),picture.getPictureDate(), picture.getUserId()));
    if ( result >= 1 ) {
      System.out.println("picture has been  added");
    }
    else {
      System.out.println("data.Picture can't be added");
    }
  }
  public ArrayList<Picture> getPictures(String userExternalId) throws SQLException {
    ArrayList<Picture> pictures=new ArrayList<>();
    ResultSet resultSet=picturesDatabase.executeQuery(String.format
      ("select * from images where user_id=(select id from users where external_id='%s')",userExternalId));
    while (resultSet.next()){
      Picture picture=new Picture();
      picture.setId(resultSet.getInt("id"));
      picture.setPictureName(resultSet.getString("name"));
      picture.setPictureDate(resultSet.getString("date"));
      picture.setUserId(resultSet.getInt("user_id"));
      pictures.add(picture);
    }
    return  pictures;
  }

  public void savaUserInDatabase(User user) throws ClassNotFoundException, SQLException {
    String password = null;
    ResultSet resultSet = picturesDatabase
      .executeQuery(String.format("select md5('%s')", user.getUserPassword()));
    while (resultSet.next()) {
      password = resultSet.getString("md5");
    }
    int result1 = picturesDatabase.executeUpdate(String.format(
      "insert into  users (name, password, external_id) values ('%s','%s', '%s')",
      user.getUserName(), password, UUID.randomUUID()));
    if (result1 >= 1) {
      System.out.println("User has been successfully added");
    } else {
      System.out.println("User can't be added");
    }

  }
  public boolean searchUserInDatabase(User user)
    throws SQLException, ClassNotFoundException {
    ResultSet resultSet = picturesDatabase.executeQuery
      (String
        .format("select * from users where name like '%s' and password like (select md5('%s'))",
          user.getUserName(), user.getUserPassword()));

    return resultSet.next();
  }
  public User getUserFromDatabase(String userName, String userPassword)
    throws SQLException, ClassNotFoundException {
    ResultSet resultSet = picturesDatabase.executeQuery(
      String.format("select * from users where name like '%s' and password like (select md5('%s'))"
        ,userName, userPassword));
    User user=new User();
    while (resultSet.next()) {
      user.setUserId(resultSet.getInt("id"));
      user.setUserName(resultSet.getString("name"));
      user.setUserPassword(resultSet.getString("password"));
      user.setExternalId(resultSet.getString("external_id"));
    }
    return user;
  }

 public void deletePictureInDatabase(int pictureId, String userExternalId)
   throws ClassNotFoundException {
  int result = picturesDatabase.executeUpdate(String
     .format("delete from images where id=%s and user_id=(select id from users where external_id='%s')", pictureId,
        userExternalId));
   if (result >= 1) {
     System.out.println("Picture has been successfully deleted");
  } else {
     System.out.println("Picture can't be deleted");
    }
 }



}