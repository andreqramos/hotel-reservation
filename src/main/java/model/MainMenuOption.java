package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public enum MainMenuOption {
    FIND_AND_RESERVE_ROOM(1, "Find and reserve a room"),
    SEE_MY_RESERVATIONS(2, "See my reservations"),
    CREATE_ACCOUNT(3, "Create an account"),
    ADMIN(4, "Admin"),
    EXIT(5, "Exit");

    private final int id;
    private final String label;
    private static final Map<Integer, MainMenuOption> map = new HashMap<>();

    MainMenuOption(int id, String label) {
        this.id = id;
        this.label = label;
    }

    static {
        for(MainMenuOption mainMenuOption: MainMenuOption.values()) {
            map.put(mainMenuOption.id, mainMenuOption);
        }
    }

    public int getId() {
        return id;
    }

    public static MainMenuOption getOption(int id) {
        if(!map.containsKey(id))
            return null;
        return map.get(id);
    }

    public static MainMenuOption getUserOption() {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Welcome to the Hotel Reservation Application");
            System.out.println("--------------------------------------------");
            MainMenuOption.printAllMainMenuOptions();
            System.out.println("--------------------------------------------");
            System.out.println("Please select a number for the menu option");
            int id = input.nextInt();
            MainMenuOption mainMenuOption = MainMenuOption.getOption(id);
            if(mainMenuOption != null)
                return mainMenuOption;
            System.out.println("Error: Invalid Input");
        }
    }

    public static void printAllMainMenuOptions() {
        for(MainMenuOption mainMenuOption: MainMenuOption.values())
            System.out.println(mainMenuOption);
    }

    @Override
    public String toString() {
        return id + ". " + label;
    }
}
