package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public enum RoomType {
    SINGLE(1),
    DOUBLE(2);

    private final int id;
    private static final Map<Integer, RoomType> map = new HashMap<>();

    RoomType(int id) {
        this.id = id;
    }

    static {
        for(RoomType roomType: RoomType.values()) {
            map.put(roomType.id, roomType);
        }
    }

    public int getId() {
        return id;
    }

    public static RoomType getOption(int id) {
        if(!map.containsKey(id))
            return null;
        return map.get(id);
    }

    public static RoomType getUserOption() {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            int id = input.nextInt();
            RoomType roomType = RoomType.getOption(id);
            if(roomType != null)
                return roomType;
            System.out.println("Error: Invalid Input");
        }
    }

}
