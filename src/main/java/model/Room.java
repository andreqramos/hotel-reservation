package model;

import service.Database;

import java.sql.SQLException;
import java.util.ArrayList;

public class Room implements IRoom{
    protected String roomNumber;
    protected Double price;
    protected RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
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

    public boolean createRoom(Room room){

        Database db = new Database();
        db.connect();

        String sql = "INSERT INTO Room(roomNumber, roomType, price) VALUES (?, ?, ?)";

        boolean check = true;
        try{
            db.pst = db.connection.prepareStatement(sql);
            db.pst.setString(1, room.getRoomNumber());
            db.pst.setRoomType(2, room.getRoomType());
            db.pst.setDouble(3, room.getRoomPrice());
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

    public ArrayList<Room> readRoom(){
        Database db = new Database();
        db.connect();
        ArrayList<Room> room = new ArrayList<>();
        String sql = "SELECT * FROM Room";
        try{
            db.statement = db.connection.createStatement();
            db.result = db.statement.executeQuery(sql);

            while(db.result.next()){
                Room roomTemp = new Room(db.result.getString("roomNumber"),
                        db.result.getString("roomType"), db.result.getString("price"));
                System.out.println("Room Number = " + roomTemp.getRoomNumber());
                System.out.println("Room Type = " + roomTemp.getRoomType());
                System.out.println("Price  = " + roomTemp.getRoomPrice());
                System.out.println("------------------------------");
                room.add(roomTemp);
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
}
