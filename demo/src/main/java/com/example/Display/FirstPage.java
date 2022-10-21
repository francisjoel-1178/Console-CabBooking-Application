package com.example.Display;

/*created by: Francis
  created on: 15/09/22 2:45PM
  Modified on: 15/09/22 3:05PM
  Reviewed by: Anushya Narayanan
  Reviewed on: 17/10/22 2:15PM
  Description: FirstPage acts as Parent class to further classes*/

  import com.example.Models.Customer;
import com.example.DatabaseManagement.DataBaseCheck;
 import com.example.DatabaseManagement.DataInsertionAndUpdation;
  import java.sql.Connection;
  import java.sql.DriverManager;
  import java.sql.PreparedStatement;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.sql.Statement;
  import java.sql.SQLException;
  import java.util.Scanner;
  public class FirstPage extends DataBaseCheck implements OptionControl,DataInsertionAndUpdation {
       private String option;
          protected static Customer customer;
  
          public String getOption() {
              return option;
          }
  
          public void setOption(String option) {
              this.option = option;
          }
  
          public void display(){
              Scanner scanner = new Scanner(System.in);
              
              System.out.println("*           * ******  *       *******  *******  *         *  ******");
              System.out.println(" *         *  *       *       *        *     *  * *     * *  *");
              System.out.println("  *       *   ******  *       *        *     *  *  *   *  *  ******");
              System.out.println("   *  *  *    *       *       *        *     *  *   * *   *  *");
              System.out.println("    *   *     ******  ******  *******  *******  *    *    *  ******");
              System.out.println("");
              System.out.println("please choose any one of the options from below");
              System.out.println("1. "+OptionControl.options.LOGIN.value);
              System.out.println("2. "+OptionControl.options.REGISTER.value);
              System.out.println("3. "+OptionControl.options.EXIT.value);
              this.option = scanner.next();
              if(this.exitApplication()) {
                  System.out.println("closing application");
              }
              else if(this.option.equals("1")){
                  Login login = new Login();
                  this.customer = new Customer();
                  login.loginPageDisplayUsername();
              }
              else if(this.option.equals("2")){
                   customer = new Customer();
                   SecondPage secondPage = new SecondPage();
                  // DataInsertionAndUpdation dInsertionAndUpdation = new DataInsertionAndUpdation();
                  
                  Login login = new Login();
                  customer.informationHandling();
                  try {
                      if(this.checkData(customer.getFirstName(), customer.getLastName())){
                          login.loginPageDisplayUsername();
                      }else{
                          this.putData();
                          secondPage.secondPageDisplay();
                      }
                      
                  } catch (SQLException exception) {
                     
                      System.out.println(exception);
                  }
  
              }
              else{
                  this.display();
              }
          }
  
          public boolean exitApplication() {
              if(this.option.equals("3")) {
                  return true;
               }
               return false;
          }
          @Override
          public boolean checkData(String firstName,String lastName) throws SQLException{
              this.url = "jdbc:mysql://localhost:3306/Aspire";
             
             String ans = "";
              String sql = "select firstName,lastName from CustomerInfo WHERE" +"firstName=?"+" AND lastName=? LIMIT 1";
              String name = firstName+lastName;
              try{
                  Class.forName("com.mysql.cj.jdbc.Driver");
                  connection = DriverManager.getConnection(url,"yourusername","yourpassword");
                  preparedStatement = connection.prepareStatement(sql);
                  preparedStatement.setString(1, firstName);
                  preparedStatement.setString(2, lastName);
                  ResultSet resultSet = preparedStatement.executeQuery(sql);
                  
                  if(resultSet.next()){
                      ans  += resultSet.getString(1);
                      ans += resultSet.getString(2);
                      if(ans.equals(name)){
                          
                          connection.close();
                          return true;
                      }
                      
                      
                      connection.close();
                      return false;
                      
                  }
                  else{
                      connection.close();
                      return false;
                  }
                    
          
                  
                  
  
              }catch(Exception exception){
                  System.out.println(exception);
                  connection.close();
                  return false;
              }
              
          }
  
          @Override
          public boolean checkUserName(String userName,String Password) throws SQLException{
              url = "jdbc:mysql://localhost:3306/Aspire";
              String username = "";
              String password = "";
              String sql = "select userName,password from CustomerInfo WHERE"+" userName=?AND password=?";
              String queryTwo = "select * from CustomerInfo";
              try {
                  Class.forName("com.mysql.cj.jdbc.Driver");
                  connection = DriverManager.getConnection(url,"yourusername","yourpassword");
                  preparedStatement = connection.prepareStatement(sql);
                  preparedStatement.setString(1, userName);
                  preparedStatement.setString(2, Password);
                  statement = connection.createStatement();
                  ResultSet resultSet = preparedStatement.executeQuery();
                  ResultSet secondResultset = statement.executeQuery(queryTwo);
                  if(resultSet.next()){
                      username += resultSet.getString(1);
                      password += resultSet.getString(2);
                      if(username.equals(userName)){
                          if(password.equals(Password)){
                              this.customer.setUserName(username);
                              this.customer.setPassword(password);
                              while(secondResultset.next()){
                                  this.customer.setId(secondResultset.getInt(1));
                                  this.customer.setFirstName(secondResultset.getString(2));
                                  this.customer.setLastName(secondResultset.getString(3));
                                  this.customer.setEmail(secondResultset.getString(4));
                                  this.customer.setMobileNumber(secondResultset.getString(5));
                                  secondResultset.getString(6);
                                  secondResultset.getString(7);
                              }
                              connection.close();
                              return true;
                          }else{
                              connection.close();
                              return false;
                              
                          }
                      }else{
                          connection.close();
                          return false;
                      }
                  }else{
                      connection.close();
                      return false;
                      
                  }
  
              } catch (Exception exception) {
                 System.out.println(exception);
                 exception.printStackTrace();
                 connection.close();
                 return false;
              }
  
  
          }
  
         
          public void putData() throws SQLException{
              url = "jdbc:mysql://localhost:3306/Aspire";
              String sql = "insert into CustomerInfo(firstName,lastName,email,mobileNumber,userName,password) values(?,?,?,?,?,?)";
              try{
                  Class.forName("com.mysql.cj.jdbc.Driver");
                  connection = DriverManager.getConnection(url,"yourusername","yourusername");
                 
                  
                  preparedStatement = connection.prepareStatement(sql);
                  
                  preparedStatement.setString(1,this.customer.getFirstName() );
                  preparedStatement.setString(2, this.customer.getLastName());
                  preparedStatement.setString(3, this.customer.getEmail());
                  preparedStatement.setString(4, this.customer.getMobileNumber());
                  preparedStatement.setString(5, this.customer.getUserName());
                  preparedStatement.setString(6, this.customer.getPassword());
                  
                  int result = preparedStatement.executeUpdate();
                  if(result==1){
                      System.out.println("data stored");
                      connection.close();
                  }
                  else{
                      System.out.println("data not stored");
                      connection.close();
                  }
                  
                  connection.close();
  
              }catch(Exception exception){
                  System.out.println(exception);
                  connection.close();
              }finally{
                  connection.close();
                  
              }
  
          }
  
         
          public void updateData() throws SQLException {
              url = "jdbc:mysql://localhost:3306/Aspire";
              String sql = "UPDATE CustomerInfo SET"+" "+"=?"+" WHERE userName=?,password=?";
  
              
          }
  }
  