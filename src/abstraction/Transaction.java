package abstraction;

import entity.Customer;

import java.io.Serializable;
import java.util.Date;

public class Transaction extends Customer implements Serializable {
    private int transactionId;
    private Customer sender;
    private Customer receiver;
    private double mount;

    public Transaction(int transactionId, Customer sender, Customer receiver, double mount) {
        super();
        this.transactionId = transactionId;
        this.sender = sender;
        this.receiver = receiver;
        this.mount = mount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public void setReceiver(Customer receiver) {
        this.receiver = receiver;
    }

    public double getMount() {
        return mount;
    }

    public void setMount(double mount) {
        this.mount = mount;
    }
}
