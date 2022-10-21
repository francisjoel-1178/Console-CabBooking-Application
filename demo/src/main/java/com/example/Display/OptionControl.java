package com.example.Display;

/*created by: Francis
  created on: 15/09/22 2:45PM
  Modified on: 15/09/22 3:05PM
  Reviewed by: Anushya Narayanan
  Reviewed on: 17/10/22 2:15PM
  Description: interface to process the closing operation*/
  

  public interface OptionControl {
      public enum options{
          EXIT("Exit"),LOGIN("Login"),REGISTER("Register"),BOOK_CAB("Book Cab"),EDIT_DETAILS("Edit Details");
          String value;
          options(String value){
              this.value=value;
          }
          
      }
      boolean exitApplication();
  }
  