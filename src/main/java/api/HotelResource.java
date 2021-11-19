package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static HotelResource instance;

    private HotelResource() {
    }

    public static HotelResource getInstance() {
        if(instance == null)
            instance = new HotelResource();
        return instance;
    }

    public Customer getCustomer(String email) {
        CustomerService customerService = CustomerService.getInstance();
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        CustomerService customerService = CustomerService.getInstance();
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = getCustomer(customerEmail);
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.findRooms(checkIn, checkOut);
    }
}
