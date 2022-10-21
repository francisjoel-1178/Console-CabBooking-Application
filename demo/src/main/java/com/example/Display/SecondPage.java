package com.example.Display;

/*created by: Francis
  created on: 15/09/22 2:45PM
  Modified on: 15/09/22 3:05PM
  Reviewed by: Anushya Narayanan
  Reviewed on: 17/10/22 2:15PM
  Description: SecondPage class is used for performing operations like edit , bookings*/
 

  import java.io.IOException;
  import java.sql.DriverManager;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.sql.Statement;
  import java.util.HashMap;
  import java.util.Map;
  import java.util.Scanner;
  
  import org.json.simple.parser.ParseException;
  
  import com.example.ApplicationController.Rides;
  public class SecondPage extends FirstPage implements OptionControl{
       private String secondPageOption;
          private String customerUserName;
          private String customerPassword;
          private String field;
          private String change;
          static Map<String,String> map;
          Scanner scanner = new Scanner(System.in);
          
  
         
          public void secondPageDisplay () {
              System.out.println("Please choose any one of options from given below");
              System.out.println("1. "+OptionControl.options.EDIT_DETAILS.value);
              System.out.println("2. "+OptionControl.options.BOOK_CAB.value);
              System.out.println("3. "+OptionControl.options.EXIT.value);
              this.secondPageOption = scanner.next();
              if(this.secondPageOption.equals("1")){
                  this.editOption();
              }else if(this.exitApplication()){
                  System.out.println("closing application");
              }
              else if(this.secondPageOption.equals("2")){
                  Rides rides = new Rides();
                  try {
                      rides.displayThirdPage();
                  } catch (ParseException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                  } catch (IOException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                  }
              }
              else{
                  this.secondPageDisplay();
              }
              
          }
          public void editOption(){
              System.out.println("1.firstName");
              System.out.println("2.lastName");
              System.out.println("3.email");
              System.out.println("4.mobileNumber");
              System.out.println("5.userName");
              System.out.println("6.password");
              System.out.print("enter the option the field you want to edit:");
              map = new HashMap<String, String>();
              map.put("1", "firstName");
              map.put("2", "lastName");
              map.put("3", "email");
              map.put("4", "mobileNumber");
              map.put("5", "userName");
              map.put("6", "password");
              this.field = scanner.next();
              System.out.println();
              if(this.field.equals("1")){
                  this.change = this.customer.firstNameValidation();
              }
              if(this.field.equals("2")){
                  this.change = this.customer.lastNameValidation();
              }
              if(this.field.equals("3")){
                  this.change = this.customer.emailValidation();
              }
              if(this.field.equals("4")){
                  this.change = this.customer.mobileNumberValidation();
              }
              if(this.field.equals("5")){
                  this.change = this.customer.userNameValidation();
              }
              if(this.field.equals("6")){
                  this.change = this.customer.passwordValidation();
              }
              try {
                  this.updateData();
              } catch (SQLException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
              this.secondPageDisplay();
  
              
          }
  
  
  
          @Override
          public boolean exitApplication() {
              if(this.secondPageOption.equals("3")) {
                  return true;
              }
              return false;
          }
  
          @Override
          public void updateData() throws SQLException {
              url = "jdbc:mysql://localhost:3306/Aspire";
              String sql = "UPDATE CustomerInfo SET"+" "+this.map.get(this.field)+"=?"+" WHERE userName=? AND password=?";
              String queryTwo = "select * from customerInfo";
              try {
                  Class.forName("com.mysql.cj.jdbc.Driver");
                  connection = DriverManager.getConnection(url,"yourusername","yourusername");
                  Statement statement = connection.createStatement();
                  
                  preparedStatement = connection.prepareStatement(sql);
                  preparedStatement.setString(1, this.change);
                  preparedStatement.setString(2, this.customer.getUserName());
                  preparedStatement.setString(3, this.customer.getPassword());
                  int result = preparedStatement.executeUpdate();
  
                  ResultSet resultset = statement.executeQuery(queryTwo);
                  while(resultset.next()){
                      this.customer.setId(resultset.getInt(1));
                      this.customer.setFirstName(resultset.getString(2));
                      this.customer.setLastName(resultset.getString(3));
                      this.customer.setEmail(resultset.getString(4));
                      this.customer.setMobileNumber(resultset.getString(5));
                      this.customer.setUserName(resultset.getString(6));
                      this.customer.setPassword(resultset.getString(7));
                  }
                  if(result==1){
                      System.out.println("data updated");
                      connection.close();
                  }else{
                      System.out.println("error");
                      connection.close();
  
                  }
  
              } catch (Exception exception) {
                  System.out.println(exception);
                  connection.close();
                  this.secondPageDisplay();
              }
              
  
              
          }
  }
  