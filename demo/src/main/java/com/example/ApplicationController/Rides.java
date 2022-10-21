package com.example.ApplicationController;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.DatabaseManagement.DataBaseCheck;

import com.example.Display.SecondPage;
public class Rides extends SecondPage {
    List<String> carSelect = new ArrayList<String>();
    Scanner scanner = new Scanner(System.in);
    private int customerId;
    private int cabDriverId;
    private String customerName;
    private String cabDriverName;
    private String customerNumber;
    private String carNumber;
    private String pickupLocation;
    private String dropLocation;
    private String typeOfCar;
    private double payableAmount;

    public Rides () {
        this.customerId = this.customer.getId();
        this.customerName = this.customer.getFirstName()+this.customer.getLastName();
        this.customerNumber = this.customer.getMobileNumber();
        

    }


    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getCabDriverId() {
        return cabDriverId;
    }
    public void setCabDriverId(int cabDriverId) {
        this.cabDriverId = cabDriverId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCabDriverName() {
        return cabDriverName;
    }
    public void setCabDriverName(String cabDriverName) {
        this.cabDriverName = cabDriverName;
    }
    public String getPickupLocation() {
        return pickupLocation;
    }
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
    public String getDropLocation() {
        return dropLocation;
    }
    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }
    public String getTypeOfCar() {
        return typeOfCar;
    }
    public void setTypeOfCar(String typeOfCar) {
        this.typeOfCar = typeOfCar;
    }
    public double getPayableAmount() {
        return payableAmount;
    }
    public void setPayableAmount(double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public void displayThirdPage() throws ParseException, IOException{
        this.carSelect.add(CarTypes.AUTO.name);
        this.carSelect.add(CarTypes.MINI.name);
        this.carSelect.add(CarTypes.PRIME.name);
        this.carSelect.add(CarTypes.SEDAN.name);
        this.pickLocationValidation();
        this.dropLocationValidation();
        this.vehicleSelection();
        try {
            this.getDriverInfo();
            this.calculateAmount();
        } catch (SQLException exception) {
            System.out.println(exception);
            this.displayThirdPage();
        }
        
    }

    public void pickLocationValidation()  {
        System.out.print("enter your pickupLocation:");
        String city = scanner.next();
        this.setPickupLocation(city);
    }

    public void dropLocationValidation() {
        System.out.print("enter your dropLocation:");
        String city = scanner.next();
        this.setDropLocation(city);
    }


    public void vehicleSelection() {
    
       
        System.out.println(CarTypes.MINI.name);
        System.out.println(CarTypes.AUTO.name);
        System.out.println(CarTypes.PRIME.name);
        System.out.println(CarTypes.SEDAN.name);
        System.out.print("please choose any one from above given type:");
        String typeOfVehicle = scanner.next();
        if(!carSelect.contains(typeOfVehicle)){
            System.out.println("enter valid options");
            this.vehicleSelection();
        }
        else{
            if(typeOfVehicle.equals(CarTypes.AUTO.name)){
                this.setTypeOfCar(CarTypes.AUTO.name);
            }
            if(typeOfVehicle.equals(CarTypes.MINI.name)){
                this.setTypeOfCar(CarTypes.MINI.name);
            }
            if(typeOfVehicle.equals(CarTypes.PRIME.name)){
                this.setTypeOfCar(CarTypes.PRIME.name);
            }
            if(typeOfVehicle.equals(CarTypes.SEDAN.name)){
                this.setTypeOfCar(CarTypes.SEDAN.name);
            }
        }
        
        

    }
    public void getDriverInfo() throws SQLException{
        url = "jdbc:mysql://localhost:3306/Aspire";
        String sql = "select id,driverName,typeOfCar,carNumber from cabInfo WHERE typeOfCar=? ORDER BY RAND() LIMIT 1";
        String QueryTwo = "DELETE FROM CabInfo WHERE driverName=?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,"username","userpassword");
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, this.typeOfCar);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                this.cabDriverId = resultSet.getInt(1);
                this.cabDriverName  = resultSet.getString(2);
                resultSet.getString(3);
                this.carNumber = resultSet.getString(4);
            }
            System.out.println("here is your cab details");
            System.out.println("cabType:"+this.typeOfCar);
            System.out.println("cabDriverName:"+this.cabDriverName);
            System.out.println("cabNumber"+this.carNumber);
            preparedStatement = connection.prepareStatement(QueryTwo);
            preparedStatement.setString(1, this.cabDriverName);
            int row = preparedStatement.executeUpdate();
            System.out.println("number of"+row+" deleted from cabInfo");
            connection.close();


        } catch (Exception exception) {
            System.out.println(exception);
            connection.close();
        }
    }
    public void calculateAmount() throws ParseException, IOException{
        System.out.println("started");
        int distance=0;
        String API_KEY = "xTPAWnRFY8pxKvfz0NMpQsHosf9Rw";
        try {
            URL url = new URL("https://api.distancematrix.ai/maps/api/distancematrix/json?origins="+this.pickupLocation+",India&destinations="+this.dropLocation+",India&key= use your key");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //opening connection
            connection.setRequestMethod("GET");
            connection.connect();
            
            int responseCode = connection.getResponseCode();
            // checking response code
            System.out.println(responseCode);
            if(responseCode!=200) {
                throw new RuntimeException("HttpResponseCode: "+responseCode);
            }else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while(scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
//	                System.out.println(informationString);
                
                JSONParser parse = new JSONParser();
                JSONObject object = (JSONObject) parse.parse(String.valueOf(informationString));
                JSONArray responseObject = new JSONArray();
                
                responseObject = (JSONArray) object.get("rows");
                object = (JSONObject) responseObject.get(0);
                responseObject = (JSONArray)object.get("elements");
                object = (JSONObject) responseObject.get(0);
                object = (JSONObject) object.get("distance");
                String ans =  (String) object.get("text");
                

                for(char num: ans.toCharArray()) {
                    if(Character.isDigit(num)) {
                        distance = distance*10+(num-'0');
                    }
                }
                System.out.print("distance that should be tavelled:"+distance);
                if(this.typeOfCar.equals(CarTypes.AUTO.name)){
                    this.payableAmount =  distance*(CarTypes.AUTO.rangeOfAmount);
                }
                if(this.typeOfCar.equals(CarTypes.MINI.name)){
                    this.payableAmount =  distance*(CarTypes.MINI.rangeOfAmount);
                }
                if(this.typeOfCar.equals(CarTypes.PRIME.name)){
                    this.payableAmount =  distance*(CarTypes.PRIME.rangeOfAmount);
                }
                if(this.typeOfCar.equals(CarTypes.SEDAN.name)){
                       this.payableAmount =  distance*(CarTypes.PRIME.rangeOfAmount);
                }

                System.out.print("payableAmount:"+this.payableAmount);
                
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
