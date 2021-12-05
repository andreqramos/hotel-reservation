package model;

import service.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Customer {

    private String firstName;
    private String lastName;
    private String customerEmail;
    public Customer(){
        this.firstName = "";
        this.lastName = "";
        this.customerEmail = "";
    }
    public Customer(String customerEmail, String firstName, String lastName) {

        Pattern pattern = Pattern.compile("[a-z0-9_.]+@[a-z]+[.]com");
        if(!pattern.matcher(customerEmail).matches())
            throw new IllegalArgumentException();

        this.firstName = firstName;
        this.lastName = lastName;
        this.customerEmail = customerEmail;
    }

    public static Customer getByTerminal() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Email format: name@domain.com");
        String customerEmail = input.nextLine();
        System.out.println("First Name");
        String firstName = input.nextLine();
        System.out.println("Last Name");
        String lastName = input.nextLine();
        return new Customer(customerEmail, firstName, lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + " Last Name: " + lastName + " Email: " + customerEmail;
    }

    public static boolean createCustomer(Customer customer){

        Database db = new Database();
        db.connect();

        String sql = "INSERT INTO Customer(customerEmail, firstName, lastName) VALUES (?, ?, ?)";

        boolean check = true;
        try{
            db.pst = db.connection.prepareStatement(sql);
            db.pst.setString(1, customer.getCustomerEmail());
            db.pst.setString(2, customer.getFirstName());
            db.pst.setString(3, customer.getLastName());
            db.pst.execute();
            check = true;
        } catch (SQLException e){
            System.out.println("Operation Error: " + e.getMessage());
            check = false;
        } finally {
            try{
                db.connection.close();
                db.pst.close();
            }catch (SQLException e){
                System.out.println("Error to close the connection: " + e.getMessage());
            }
        }
        return check;
    }

    public boolean updateCustomer(String firstName, String lastName, String customerEmail ){

        Database db = new Database();
        db.connect();
        String sql = "UPDATE Customer SET firstName=?, lastName=? WHERE customerEmail=?";
        boolean check = true;

        try{
            db.pst = db.connection.prepareStatement(sql);
            db.pst.setString(1, firstName);
            db.pst.setString(2, lastName);
            db.pst.setString(3, customerEmail);
            db.pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Operation Error: " + e.getMessage());
            check = false;
        }finally {
            try {
                db.connection.close();
                db.pst.close();
            }catch (SQLException e) {
                System.out.println("Error to close the connection: " + e.getMessage());
            }
        }
        return check;
    }

    public boolean deleteCustomer(String customerEmail) {

        Database db = new Database();
        db.connect();
        String sql = "DELETE FROM Customer WHERE customerEmail=?";
        boolean check = true;

        try{
            db.pst = db.connection.prepareStatement(sql);
            db.pst.setString(1, customerEmail);
            db.pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Operation Error: " + e.getMessage());
            check = false;
        }finally {
            try {
                db.connection.close();
                db.pst.close();
            }catch (SQLException e){
                System.out.println("Error to close the connection: " + e.getMessage());
            }
        }
        return check;
    }
}
