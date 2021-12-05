package model;

import service.Database;

import java.sql.SQLException;
import java.util.Date;

public class Reservation {

    private String reservationId;
    private String customerEmail;
    private String roomNumber;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(String reservationId, String customerEmail, String roomNumber, Date checkInDate, Date checkOutDate) {
        this.reservationId = reservationId;
        this.customerEmail = customerEmail;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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
                "customer=" + customerEmail +
                ", room=" + roomNumber +
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
            db.pst.setDate(2, (java.sql.Date) reservation.getCheckInDate());
            db.pst.setDate(3, (java.sql.Date) reservation.getCheckInDate());
            db.pst.setString(4,reservation.getCustomerEmail());
            db.pst.setString(5, reservation.getRoomNumber());
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

    public boolean updateReservation(String reservationId, Date checkInDate, Date checkOutDate, String customerEmail,
                                     String roomNumber){

        Database db = new Database();
        db.connect();
        String sql = "UPDATE Reservation SET checkInDate=?, checkOutDate=?, customerEmail=?," +
                     "roomNumber=? WHERE reservationId=?";
        boolean check = true;

        try{
            db.pst = db.connection.prepareStatement(sql);
            db.pst.setDate(1, (java.sql.Date) checkInDate);
            db.pst.setDate(2, (java.sql.Date) checkOutDate);
            db.pst.setString(3, customerEmail);
            db.pst.setString(4, roomNumber);
            db.pst.setString(5, reservationId);
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

    public boolean deleteReservation(String reservationId) {

        Database db = new Database();
        db.connect();
        String sql = "DELETE FROM Reservation WHERE reservationId=?";
        boolean check = true;

        try{
            db.pst = db.connection.prepareStatement(sql);
            db.pst.setString(1, reservationId);
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
