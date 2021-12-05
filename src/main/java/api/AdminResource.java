package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.Database;
import service.ReservationService;

import java.sql.SQLException;
import java.util.*;

public final class AdminResource {

    private static AdminResource instance;

    private AdminResource() {
    }

    public static AdminResource getInstance() {
        if(instance == null)
            instance = new AdminResource();
        return instance;
    }

    public Customer getCustomer(String email) {
        CustomerService customerService = CustomerService.getInstance();
        return customerService.getCustomer(email);
    }


    public void addRoom(List<IRoom> rooms) {
        ReservationService reservationService = ReservationService.getInstance();
        for(IRoom room: rooms)
            reservationService.addRoom(room);
    }

    public Collection<IRoom> getAllRooms() {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.getAllRooms();
    }


    public Collection<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
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
                customers.add(customerTemp);
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
        return customers;
    }

    public void displayAllCustomers() {
        Collection<Customer> customers = getAllCustomers();
        for(Customer customer: customers)
            System.out.println(customer);
    }

    public void displayAllRooms() {
        ReservationService reservationService = ReservationService.getInstance();
        reservationService.printAllRooms();
    }

    public void displayAllReservations() {
        ReservationService reservationService = ReservationService.getInstance();
        reservationService.printAllReservation();
    }
}
