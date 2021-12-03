package model;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("j@domain.com", "first", "second");
        System.out.println(customer);

        Customer customerFail = new Customer("email", "first", "second");
    }
}
