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
                    AdminResource adminResource = AdminResource.getInstance();
                    adminResource.addRoom();
                }
                case BACK_TO_MAIN_MENU -> {
                    return;
                }
            }
        }
    }
}
