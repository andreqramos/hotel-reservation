package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CustomerService {
    private static CustomerService instance;
    static Map<String, Customer> customers;

    private CustomerService() {
        customers = new HashMap<>();
    }

    public static CustomerService getInstance() {
        if(instance == null)
            instance = new CustomerService();
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(email, firstName, lastName);
        customers.put(customer.getCustomerEmail(), customer);
    }

    public Customer getCustomer(String customerEmail) {
        if(customers.containsKey(customerEmail))
            return customers.get(customerEmail);
        System.out.printf("Customer with email '%s' was not found.", customerEmail);
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    public void printAllCustomers() {
        for(Customer customer: customers.values())
            System.out.println(customer);
    }
}
