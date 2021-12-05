package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class HotelResource {

    private static HotelResource instance;

    private HotelResource() {
    }

    public static HotelResource getInstance() {
        if(instance == null)
            instance = new HotelResource();
        return instance;
    }

    public Customer getCustomerByCustomerEmail(String customerEmail) {
        CustomerService customerService = CustomerService.getInstance();
        return customerService.getCustomerByCustomerEmail(customerEmail);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        CustomerService customerService = CustomerService.getInstance();
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, String roomNumber, Date checkInDate, Date checkOutDate) {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.reserveARoom(customerEmail, roomNumber, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Email format: name@domain.com");
        String customerEmail = input.nextLine();
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.getCustomerReservations(customerEmail);
    }

    public void displayCustomerReservations(){
        Collection<Reservation> reservations = getCustomerReservations();
        for (Reservation reservation :
                reservations) {
            System.out.println(reservation);
        }
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.findRooms(checkIn, checkOut);
    }
}
