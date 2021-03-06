package cli;

import api.HotelResource;
import model.Customer;
import model.MainMenuOption;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        while(true) {
            MainMenuOption mainMenuOption = MainMenuOption.getUserOption();
            switch (mainMenuOption) {
                case FIND_AND_RESERVE_ROOM -> {
                    Scanner input = new Scanner(System.in);
                    System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
                    String checkInDateString = input.nextLine();
                    System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2020");
                    String checkOutDateString = input.nextLine();
                    System.out.println("Would you like to book a room? y/n");
                    byte bookARoom = input.nextByte();
                    System.out.println("Do you have an account with us? y/n");
                    byte haveAnAccount = input.nextByte();
                    System.out.println("Enter Email format: name@domain.com");
                    String customerEmail = input.nextLine();
                    System.out.println("What room number would you like to reserve");
                    String roomNumber = input.nextLine();
                }
                case SEE_MY_RESERVATIONS -> {
                    HotelResource hotelResource = HotelResource.getInstance();
                    hotelResource.displayCustomerReservations();
                }
                case CREATE_ACCOUNT -> {
                    Customer customer = Customer.getByTerminal();
                    Customer.createCustomer(customer);
                }
                case ADMIN -> AdminMenu.main();
                case EXIT -> {
                    return;
                }
            }
        }
    }
}
