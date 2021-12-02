package model;

import java.util.regex.Pattern;

public class Customer {

    private String firstName;
    private String lastName;
    private String customerEmail;


    public Customer(String firstName, String lastName, String customerEmail) {

        Pattern pattern = Pattern.compile("[a-z0-9_.]+@[a-z]+[.]com");
        if(!pattern.matcher(customerEmail).matches())
            throw new IllegalArgumentException();

        this.firstName = firstName;
        this.lastName = lastName;
        this.customerEmail = customerEmail;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + " Last Name: " + lastName + " Email: " + customerEmail;
    }
}
