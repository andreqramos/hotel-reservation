package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public enum AdminMenuOption {
    SEE_ALL_CUSTOMERS(1, "See all Customers"),
    SEE_ALL_ROOMS(2, "See all Rooms"),
    SEE_ALL_RESERVATIONS(3, "See all Reservations"),
    ADD_ROOM(4, "Add a Room"),
    BACK_TO_MAIN_MENU(5, "Back to Main Menu");

    private final int id;
    private final String label;
    private static final Map<Integer, AdminMenuOption> map = new HashMap<>();

    AdminMenuOption(int id, String label) {
        this.id = id;
        this.label = label;
    }

    static {
        for(AdminMenuOption adminMenuOption: AdminMenuOption.values()) {
            map.put(adminMenuOption.id, adminMenuOption);
        }
    }

    public int getId() {
        return id;
    }

    public static AdminMenuOption getOption(int id) {
        if(!map.containsKey(id))
            return null;
        return map.get(id);
    }

    public static AdminMenuOption getUserOption() {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Admin Menu");
            System.out.println("--------------------------------------------");
            AdminMenuOption.printAllAdminMenuOptions();
            System.out.println("--------------------------------------------");
            System.out.println("Please select a number for the menu option");
            int id = input.nextInt();
            AdminMenuOption adminMenuOption = AdminMenuOption.getOption(id);
            if(adminMenuOption != null)
                return adminMenuOption;
            System.out.println("Error: Invalid Input");
        }
    }

    public static void printAllAdminMenuOptions() {
        for(AdminMenuOption adminMenuOption: AdminMenuOption.values())
            System.out.println(adminMenuOption);
    }

    @Override
    public String toString() {
        return id + ". " + label;
    }
}
