package com.example.Display;

/*created by: Francis
  created on: 15/09/22 2:45PM
  Modified on: 15/09/22 3:05PM
  Reviewed by: Anushya Narayanan
  Reviewed on: 17/10/22 2:15PM
  Description: Login Validates username and passsword*/

  import java.sql.SQLException;
  import java.util.Scanner;
  public class Login extends FirstPage {
      String loginPageOption;
      SecondPage secondPage;
      private String userName;
      private String password;
      
  
      public String getUserName() {
          return userName;
      }
      public void setUserName(String userName) {
          this.userName = userName;
      }
      public String getPassword() {
          return password;
      }
      public void setPassword(String password) {
          this.password = password;
      }
  
  
      Scanner scanner = new Scanner(System.in);
  
  
      public void loginPageDisplayUsername(){
          System.out.println("enter username and password");
          System.out.print("username:");
          this.userName = scanner.next();
          System.out.println();
          System.out.print("password:");
          this.password = scanner.next();
        
          try {
              if(!this.checkUserName(this.userName, this.password)){
                  System.out.println("Incorrect username and password please re-enter");
                  this.loginPageDisplayUsername();
              }
              else{
                  secondPage = new SecondPage();
                  System.out.println("username and password matched ");
                  secondPage.secondPageDisplay();
              }
          } catch (SQLException exception) {
              System.out.println(exception);
              this.loginPageDisplayUsername();
              
          }
          
          
      }
  }
  
