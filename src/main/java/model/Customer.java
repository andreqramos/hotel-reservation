package model;

import service.Database;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Customer {

    private String firstName;
    private String lastName;
    private String customerEmail;


    public Customer(String firstName, String lastName, String customerEmail) {

        Pattern pattern = Pattern.compile("[a-z0-9_.]+@[a-z]+[.]com");
        if(!pattern.matcher(customerEmail).matches())
            throw new IllegalArgumentException();

        this.firstName = firstName;
        this.lastName = lastName;
        this.customerEmail = customerEmail;
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

    public boolean createCustomer(Customer customer){

        Database db = new Database();
        db.connect();

        String sql = "INSERT INTO Costumer(customerEmail, firstName, lastName) VALUES (?, ?, ?)";

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
}
