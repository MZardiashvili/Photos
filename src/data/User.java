package data;

public class User {
  private int userId;
  private String userName;
  private String userPassword;
  private String externalId;

  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String extrenalId) {
    this.externalId = extrenalId;
  }


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }
}
