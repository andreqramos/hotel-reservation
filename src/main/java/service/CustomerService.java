package service;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CustomerService {
    private static CustomerService instance;
    static Map<String, Customer> customers;

    private CustomerService() {
        customers = new HashMap<>();
    }

    public static CustomerService getInstance() {
        if(instance == null)
            instance = new CustomerService();
        return instance;
    }

    public void addCustomer(String customerEmail, String firstName, String lastName) {
        Customer customer = new Customer(customerEmail, firstName, lastName);
        customers.put(customer.getCustomerEmail(), customer);
    }

    public Customer getCustomerByCustomerEmail(String customerEmail) {
        Database db = new Database();
        db.connect();
        String sql = "SELECT * FROM Customer WHERE customerEmail=" + customerEmail;

        Customer customer = null;
        try{
            db.statement = db.connection.createStatement();
            db.result = db.statement.executeQuery(sql);

            while(db.result.next()){
                String firstName = db.result.getString("firstName");
                String lastName = db.result.getString("lastName");
                customer = new Customer(customerEmail, firstName, lastName);
            }
        }catch (SQLException e){
            System.out.println("Operation Error: " + e.getMessage());
        }finally {
            try {
                db.connection.close();
                db.statement.close();
                db.result.close();
            }catch (SQLException e){
                System.out.println("Error to close the connection: " + e.getMessage());
            }
        }
        if(customer == null)
            System.out.printf("Customer with email '%s' was not found.", customerEmail);
        return customer;
    }

    public Collection<Customer> getAllCustomers() {
        Database db = new Database();
        db.connect();
        String sql = "SELECT * FROM Customer";
        try{
            db.statement = db.connection.createStatement();
            db.result = db.statement.executeQuery(sql);
            while(db.result.next()){
                String customerEmail = db.result.getString("customerEmail");
                String firstName = db.result.getString("firstName");
                String lastName = db.result.getString("lastName");
                Customer customerTemp = new Customer(customerEmail, firstName, lastName);
                customers.put(customerTemp.getCustomerEmail(), customerTemp);
            }
        }catch (SQLException e){
            System.out.println("Operation Error: " + e.getMessage());
        }finally {
            try {
                db.connection.close();
                db.statement.close();
                db.result.close();
            }catch (SQLException e){
                System.out.println("Error to close the connection: " + e.getMessage());
            }
        }
        return customers.values();
    }

    public void printAllCustomers() {
        getAllCustomers();
        for(Customer customer: customers.values())
            System.out.println(customer);
    }
}
