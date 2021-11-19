package model;

public class Room implements IRoom{
    protected String roomNumber;
    protected Double price;
    protected RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return enumeration;
    }

    public boolean isFree() {
        return price == 0.0;
    }

    @Override
    public String toString() {
        if(enumeration.equals(RoomType.SINGLE))
            return "Room Number: " + roomNumber +   " Single bed Room Price: $" + price;
        return "Room Number: " + roomNumber +   " Double bed Room Price: $" + price;
    }
}
