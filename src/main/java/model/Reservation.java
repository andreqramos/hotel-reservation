package model;

import service.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Reservation {

    private String reservationId;
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(String reservationId, Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                "customer=" + customer +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }

    public boolean createReservation(Reservation reservation){

        Database db = new Database();
        db.connect();

        String sql = "INSERT INTO Reservation(reservationId, checkInDate, checkOutDate, customerEmail, roomNumber) VALUES (?, ?, ?, ?, ?)";

        boolean check = true;
        try{
            db.pst = db.connection.prepareStatement(sql);
            db.pst.setString(1, reservation.getReservationId());
            db.pst.setDate(2, reservation.getCheckInDate());
            db.pst.setDate(3, reservation.getCheckInDate());
            db.pst.setString(4,reservation.getCustomer().getCustomerEmail());
            db.pst.setString(5, reservation.getRoom().getRoomNumber());
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

    public ArrayList<Reservation> readReservation(){
        Database db = new Database();
        db.connect();
        ArrayList<Reservation> reservation = new ArrayList<>();
        String sql = "SELECT * FROM Reservation";
        try{
            db.statement = db.connection.createStatement();
            db.result = db.statement.executeQuery(sql);

            while(db.result.next()){
                Reservation reservationTemp = new Reservation(db.result.getString("reservationId"),
                        db.result.getDate("checkInDate"), db.result.getDate("checkOutDate"),
                        db.result.getString("customerEmail"), db.result.getString("roomNumber"));
                System.out.println("ReservationId = " + reservationTemp.getReservationId());
                System.out.println("CheckInDate = " + reservationTemp.getCheckInDate());
                System.out.println("CheckOutDate = " + reservationTemp.getCheckOutDate());
                System.out.println("Customer Email = " + reservationTemp.getCustomer().getCustomerEmail();
                System.out.println("Room Number" = + reservationTemp.getRoom().getRoomNumber());
                System.out.println("------------------------------");
                customer.add(reservationTemp);
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
        return reservation;
    }
}
