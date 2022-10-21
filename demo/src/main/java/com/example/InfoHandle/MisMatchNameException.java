package com.example.InfoHandle;

/*created by: Francis
  created on: 15/09/22 2:45PM
  Modified on: 15/09/22 3:05PM
  Reviewed by: Anushya Narayanan
  Reviewed on: 17/10/22 2:15PM
  Description: Custom Exceptions*/


  public class MisMatchNameException extends Exception {
       public MisMatchNameException(String message) {
              super(message);
          }
          public MisMatchNameException(Throwable cause) {
              super(cause);
          }
          public MisMatchNameException(String message,Throwable cause) {
              super(message,cause);
              
          }
  }
  