package service;

import abstraction.Transaction;
import entity.Address;
import entity.Customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankService implements Serializable {
    private static int lastTransactionId;
    private static Scanner scanner;

    static {
        lastTransactionId = 0;
        scanner = new Scanner(System.in);
    }

    public static void seeBlance(Customer customer) {
        double balance = customer.getBalance();
        System.out.println("Balance:" + balance);
        UserService.mainMenu(customer);
    }

    public static void withdrawMoney(Customer customer) {
        System.out.println("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        double balance = customer.getBalance();

        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            customer.setBalance(balance);

            System.out.println("Withdrawal of " + amount + " successful.");
            System.out.println("New balance: " + balance);
        } else System.out.println("Invalid withdrawal amount or insufficient ");
        UserService.mainMenu(customer);
    }

    public static void transferMoney(Customer customer) {
        Customer sender = new Customer();
        Customer recipient = new Customer();
        double amount = scanner.nextDouble();
        scanner.nextLine();
        double senderBalance = sender.getBalance();

        if (amount > 0 && amount <= senderBalance) {
            double recipientBalance = recipient.getBalance();

            senderBalance -= amount;
            recipientBalance += amount;

            sender.setBalance(senderBalance);
            recipient.setBalance(recipientBalance);

            int transactionId = generateTransaction();
            Transaction transaction = new Transaction(transactionId, sender, recipient, amount);
            sender.getTransactions().add(transaction);
            recipient.getTransactions().add(transaction);

            System.out.println("Transfer of $" + amount + " successful.");
            System.out.println("New balance for sender: $" + senderBalance);
            System.out.println("New balance for recipient: $" + recipientBalance);
        } else System.out.println("Invalid transfer amount or insufficient balance.");
        UserService.mainMenu(customer);
    }

    private static int generateTransaction() {
        lastTransactionId++;
        return lastTransactionId;
    }

    public static void reviewTheTransaction(Customer customer) {
        List<Transaction> transactions = customer.getTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactions) {
                System.out.println("Transaction ID: " + transaction.getTransactionId());
                System.out.println("Sender: " + transaction.getSender().getUserName());
                System.out.println("Recipient: " + transaction.getReceiver().getUserName());
                System.out.println("Amount: $" + transaction.getMount());
                System.out.println("---------------------------");
            }
        }
        UserService.mainMenu(customer);
    }

    public static void shipMoneyToAddress(Customer customer) {
        Address address = new Address();
        Address recipientAddress = new Address();
        System.out.println("Enter the recipient's city: ");
        recipientAddress.setCity(scanner.nextLine());

        System.out.println("Enter the recipient's district: ");
        recipientAddress.setDistrict(scanner.nextLine());

        System.out.println("Enter the recipient's house number: ");
        recipientAddress.setHouseNumber(scanner.nextLine());

        System.out.println("Enter the amount to ship: ");
        double amountToShip = scanner.nextDouble();
        scanner.nextLine();

        double balance = customer.getBalance();

        if (amountToShip > 0 && amountToShip <= balance) {
            balance -= amountToShip;
            customer.setBalance(balance);

            System.out.println("Money shipped successfully to address: " + address);
            System.out.println("New balance: $" + balance);
        } else {
            System.out.println("Invalid amount or insufficient balance to ship.");
        }

        UserService.mainMenu(customer);
    }
}
