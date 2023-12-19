package entity;


import abstraction.Transaction;
import abstraction.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements Serializable {
    protected int customerId;
    private static int count;
    private double balance;

    private List<Transaction> transactions = new ArrayList<>();

    public Customer() {

    }

    public Customer(String username, String phoneNumber, String email, String password, Address address) {
        super(username, phoneNumber, email, password, address);
        this.customerId = count++;
        this.balance = 1000000.0D;
    }

    public Customer(String phoneNumber, String email, String address) {
        super(phoneNumber, email, address);
        this.customerId = count++;
    }

    public Customer(String username, String password) {
        super(username, password);
        this.customerId = count++;
        this.balance = 1000.0D;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
