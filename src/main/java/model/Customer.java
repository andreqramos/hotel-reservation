package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {

    private String firstName;
    private String lastName;
    private String email;


    public Customer(String firstName, String lastName, String email) {

        Pattern pattern = Pattern.compile("[a-z0-9_.]+@[a-z]+[.]com");
        if(!pattern.matcher(email).matches())
            throw new IllegalArgumentException();

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + " Last Name: " + lastName + " Email: " + email;
    }
}
