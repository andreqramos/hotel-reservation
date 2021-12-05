package model;

import service.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Room implements IRoom{
    protected String roomNumber;
    protected Double price;
    protected RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public Room(){
        this.roomNumber = "";
        this.price = 0.0;
        this.roomType = RoomType.SINGLE;
    }

    public static Room getByTerminal() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter room number");
        String roomId = input.nextLine();
        System.out.println("Enter price per night");
        Double roomPrice = input.nextDouble();
        RoomType roomType = RoomType.getUserOption();
        return new Room(roomId, roomPrice, roomType);
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public boolean isFree() {
        return price == 0.0;
    }

    @Override
    public String toString() {
        if(roomType.equals(RoomType.SINGLE))
            return "Room Number: " + roomNumber +   " Single bed Room Price: $" + price;
        return "Room Number: " + roomNumber +   " Double bed Room Price: $" + price;
    }

    public static Room researchRoom(String roomNumber){
        Database db = new Database();
        db.connect();
        String sql = "SELECT * FROM Room WHERE roomNumber=" + roomNumber;

        Room room = new Room();
        try{
            db.statement = db.connection.createStatement();
            db.result = db.statement.executeQuery(sql);

            while(db.result.next()){
                Double price = db.result.getDouble("price");
                RoomType roomType = RoomType.getOption(db.result.getInt("roomType"));
                room = new Room(roomNumber, price, roomType);
                System.out.println("roomNumber = " + room.getRoomNumber());
                System.out.println("price = " + room.getRoomPrice());
                System.out.println("roomType = " + room.getRoomType());
                System.out.println("------------------------------");
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
        return room;
    }

    public boolean updateRoom(String roomNumber, RoomType roomType, Double price ){

        Database db = new Database();
        db.connect();
        String sql = "UPDATE Room SET roomType=?, price=? WHERE roomNumber=?";
        boolean check = true;

        try{
            db.pst = db.connection.prepareStatement(sql);
            db.pst.setInt(1, roomType.getId());
            db.pst.setDouble(2, price);
            db.pst.setString(3, roomNumber);
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

    public boolean deleteRoom(String roomNumber) {

        Database db = new Database();
        db.connect();
        String sql = "DELETE FROM Room WHERE roomNumber=?";
        boolean check = true;

        try{
            db.pst = db.connection.prepareStatement(sql);
            db.pst.setString(1, roomNumber);
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
