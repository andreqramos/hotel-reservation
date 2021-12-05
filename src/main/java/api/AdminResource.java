package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

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
        CustomerService customerService = CustomerService.getInstance();
        return customerService.getAllCustomers();
    }

    public void displayAllCustomers() {
        CustomerService customerService = CustomerService.getInstance();
        customerService.printAllCustomers();
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
