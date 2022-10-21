package com.example.Models;

/*created by: Francis
  created on: 15/09/22 2:45PM
  Modified on: 15/09/22 3:05PM
  Reviewed by: Anushya Narayanan
  Reviewed on: 17/10/22 2:15PM
  Description: Customer Class to store details*/
  
  import java.util.InputMismatchException;
 
  import java.util.Scanner;
  import java.util.regex.Matcher;
  import java.util.regex.Pattern;

import com.example.InfoHandle.MisMatchNameException;
import com.example.InfoHandle.NumberMatchException;

import java.lang.Math;
  
  public class Customer {
        private static int id;
          private String firstName;
          private String lastName;
          private String email;
          private String mobileNumber;
          private String userName;
          private String password;
          
          
  
  
          Scanner scanner = new Scanner(System.in);
          public Customer(){
              
          }
  
          public int getId() {
              return id;
          }
          public void setId(int id) {
              this.id = id;
          }
          public String getFirstName() {
              return firstName;
          }
          public void setFirstName(String firstName) {
              this.firstName = firstName;
          }
          public String getLastName() {
              return lastName;
          }
          public void setLastName(String lastName) {
              this.lastName = lastName;
          }
          public String getMobileNumber() {
              return mobileNumber;
          }
          public void setMobileNumber(String mobileNumber) {
              this.mobileNumber = mobileNumber;
          }
          
           
          
          public String getEmail() {
              return email;
          }
  
          public void setEmail(String email) {
              this.email = email;
          }
          
  
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
  
          public void informationHandling()  { // validation methods setting the information
              
              this.setFirstName(this.firstNameValidation()); 
              this.setLastName(this.lastNameValidation());
              this.setMobileNumber(this.mobileNumberValidation());
              this.setEmail(this.emailValidation());
              this.setUserName(this.userNameValidation());
              this.setPassword(this.passwordValidation());
              
              
              
          }
          public String firstNameValidation() {
              System.out.print("enter your firstname:");
              String name = scanner.next();
              System.out.println();
              
              Pattern pattern  = Pattern.compile("[A-Z][a-z]*");
              Matcher matcher  = pattern.matcher(name);
              boolean matched = matcher.matches();
              try {
                  if(name.length()<3){
                      throw new MisMatchNameException("please enter a valid name and start with capital letter");
                  }
                  if(matched){
                      return name;
                  }else{
                      throw new MisMatchNameException("please enter a valid name and start with capital letter");
                  }
              } catch (Exception exception) {
                  System.out.println(exception);
                  return this.firstNameValidation();
              }
              
          }
          public String lastNameValidation() {
              System.out.print("enter your lastname:");
              String name = scanner.next();
              System.out.println();
              
              Pattern pattern  = Pattern.compile("[A-Z][a-z]*");
              Matcher matcher  = pattern.matcher(name);
              boolean matched = matcher.matches();
              try {
                  if(name.length()<3){
  
                      throw new MisMatchNameException("please enter a valid name and start with capital letter");
                  }
                  if(matched){
                      return name;
                  }else{
                      throw new MisMatchNameException("please enter a valid name and start with capital letter");
                     
                      
                  }
              } catch (Exception exception) {
                  System.out.println(exception);
                  return lastNameValidation();
              }
              
          }
          public String mobileNumberValidation()  {
              System.out.print("enter your mobilenumber:");
            
              try {
                 long num = scanner.nextLong();
                 String number = String.valueOf(num);
                 
                 if(number.length()!=10){
                  throw new NumberMatchException("enter only 10 digits");
                 }
                 System.out.println();
                 return number;
              } catch (NumberMatchException exception) {
                  System.out.println(exception);
                  // scanner.nextLine();
                  return this.mobileNumberValidation();
              } catch(InputMismatchException exception){
                  System.out.println(exception);
                  
                  scanner.nextLine();
                  return this.mobileNumberValidation();
              }
              
              
              
             
          }
         
          
          public String emailValidation(){
              System.out.print("enter your valid mail id:");
              try {
                  String Email = scanner.next(); 
                  Pattern pattern  = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
                  Matcher matcher  = pattern.matcher(Email);
                  boolean matched = matcher.matches();
                  if(matched){
                      System.out.println();
                      return Email;
                  }else{
                      throw new Exception("enter valid email id");
                  }
  
              } catch (Exception exception) {
                  System.out.println(exception);
                  return this.emailValidation();
              }
          }
  
          public String userNameValidation(){
              System.out.println("username constraints it can contain 6-30 alphanumeric characters with underscores allowed and no special characters allowed");
              System.out.println("sample: francis12_");
              System.out.print("enter username:");
              try {
                  String userName = scanner.next(); 
                  Pattern pattern  = Pattern.compile("^[A-Za-z]\\w{5,29}$");
                  Matcher matcher  = pattern.matcher(userName);
                  boolean matched = matcher.matches();
                  if(matched){
                      System.out.println();
                      return userName;
                  }else{
                      throw new Exception("username constraints not satisfied enter again");
                  }
  
              } catch (Exception exception) {
                  System.out.println(exception);
                  return this.userNameValidation();
              }
          }
          public String passwordValidation(){
              System.out.println("username constraints it can contain 8-20 all special characters, numbers, lowercase, uppercase letters must occur once and no white space allowed  ");
              System.out.println("sample: Fra**(i3n%32");
              System.out.print("enter password:");
              try {
                  String passWord = scanner.next(); 
                  Pattern pattern  = Pattern.compile("^[a-zA-Z@#$%^&+=](?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}[a-zA-Z0-9]$");
                  Matcher matcher  = pattern.matcher(passWord);
                  boolean matched = matcher.matches();
                  if(matched){
                      System.out.println();
                      return passWord;
                  }else{
                      throw new Exception("password constraints not satisfied enter again");
                  }
  
              } catch (Exception exception) {
                  System.out.println(exception);
                  return this.passwordValidation();
              }
          }
  
  
          @Override
          public String toString() {
              return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber="
                      + mobileNumber +   "]";
          }
  }
  
