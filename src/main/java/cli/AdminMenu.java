package cli;

import api.AdminResource;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AdminMenu {
    public static void main() {
        while (true) {
            AdminMenuOption adminMenuOption = AdminMenuOption.getUserOption();
            switch (adminMenuOption) {
                case SEE_ALL_CUSTOMERS -> {
                    AdminResource adminResource = AdminResource.getInstance();
                    adminResource.displayAllCustomers();
                }
                case SEE_ALL_ROOMS -> {
                    AdminResource adminResource = AdminResource.getInstance();
                    adminResource.displayAllRooms();
                }
                case SEE_ALL_RESERVATIONS -> {
                    AdminResource adminResource = AdminResource.getInstance();
                    adminResource.displayAllReservations();
                }
                case ADD_ROOM -> {
                    List<IRoom> rooms = AdminMenu.getRooms();
                    AdminResource adminResource = AdminResource.getInstance();
                    adminResource.addRoom(rooms);
                }
                case BACK_TO_MAIN_MENU -> {
                    return;
                }
            }
        }
    }

    private static List<IRoom> getRooms() {
        List<IRoom> rooms = new ArrayList<>();
        byte runAgain;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter room number");
            String roomId = input.nextLine();
            System.out.println("Enter price per night");
            Double roomPrice = input.nextDouble();
            RoomType roomType = RoomType.getUserOption();
            IRoom room = new Room(roomId, roomPrice, roomType);
            rooms.add(room);
            System.out.println("Would you like to add another room y/n");
            runAgain = input.nextByte();
        } while(runAgain == 'y');
        return rooms;
    }
}
