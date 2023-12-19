package service;


import abstraction.Transaction;
import abstraction.User;
import constant.Constants;
import entity.Address;
import entity.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService implements Serializable {
    private static final List<Customer> CUSTOMER_LIST;
    private static List<Customer> customers;
    private static final UserService userService;
    private static boolean checkCustomer = false;
    private static Scanner scanner;
    private static final File FILE_CUSTOMER;

    static {
        userService = new UserService();
        customers = new ArrayList<>();
        FILE_CUSTOMER = new File("src/file_customer.txt");
        CUSTOMER_LIST = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    private UserService() {
    }

    public static UserService getInstance() {
        return userService;
    }

    public static void run() {
        loadData();
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Register User");
            System.out.println("2. Log In");
            System.out.println("3. Find customer");
            System.out.println("4. Log Out");
            System.out.println("----------------------------------------------");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case Constants.REGISTER:
                    UserService.register();
                    break;
                case Constants.LOGIN:
                    UserService.login();
                case Constants.FIND:
                    findCustomer();
                    break;
                case Constants.LOGOUT:
                    UserService.logout();
                    break;
                default:
                    System.out.println("\t Winzbank hẹn gặp lại quý khách");
            }
        }
    }

    public static void mainMenu(Customer customer) {
        int menu;
        System.out.println("----------------------------------------------");
        System.out.println("\tWelcome to WinzBank");
        System.out.println("1.See balance");
        System.out.println("2.Withdraw money");
        System.out.println("3.Transfer money");
        System.out.println("4.Review the transaction");
        System.out.println("5.Ship Money To Address");
        System.out.println("6.Exit");
        menu = scanner.next().charAt(0);
        scanner.nextLine();

        switch (menu) {
            case Constants.SEE_BALANCE:
                BankService.seeBlance(customer);
                break;
            case Constants.WITHDRAW_MONEY:
                BankService.withdrawMoney(customer);
                break;
            case Constants.TRANSFER_MONEY:
                BankService.transferMoney(customer);
            case Constants.REVIEW_TRANSACTIOM:
                BankService.reviewTheTransaction(customer);
                break;
            case Constants.SHIP_MONEY_TO_ADDRESS:
                BankService.shipMoneyToAddress(customer);
                break;
            case '6':
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid entry. Please re-enter");
        }
    }

    private static void logout() {
    }

    private static void register() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter address: ");
        Address address = createAddress();

        boolean isUserNameExists = isUserNameExists(username);
        if (isUserNameExists) {
            System.out.println("Username already exists. Please choose another username.");
        } else {
            Customer customer = new Customer(username, phoneNumber, email, password, address);
            customers.add(customer);
            System.out.println("Register successful!");
            System.out.println("----------------------------------------------");
        }
        saveToFile();
    }

    private static Address createAddress() {
        Address address;
        System.out.println("Enter house number");
        String houseNumber = scanner.next();
        System.out.println("Enter district");
        String district = scanner.next();
        System.out.println("Enter city");
        String city = scanner.next();
        address = new Address()
                .houseNumber(houseNumber)
                .district(district)
                .city(city);
        return address;
    }

    private static void saveToFile() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_CUSTOMER));
            objectOutputStream.writeObject(customers);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadData() throws RuntimeException {
        if (FILE_CUSTOMER.length() != 0) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_CUSTOMER));
                customers = (List<Customer>) objectInputStream.readObject();
                objectInputStream.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static boolean isUserNameExists(String username) {
        for (Customer customer : customers) {
            if (customer.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static void login() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        Customer customer = loggingIn(username, password);
        if (customer != null) {
            mainMenu(customer);
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static Customer loggingIn(String username, String password) {
        for (Customer customer : customers) {
            if ((customer.getUserName().equals(username) && customer.getPassword().equals(password))) {
                return customer;
            }
        }
        return null;
    }

    public static void findCustomer() {
        System.out.println("How would you like to search?");
        System.out.println("1. Search by name");
        System.out.println("2. Search by email");
        System.out.println("3. Search by phone number");

        int findCustomerFind = scanner.nextInt();
        scanner.nextLine();
        IFind iFind = null;

        switch (findCustomerFind) {
            case Constants.FIND_NAME -> iFind = new FindByName();
            case Constants.FIND_EMAIL -> iFind = new FindByEmail();
            case Constants.FIND_PHONE_NUMBER -> iFind = new FindByPhoneNumber();
        }
        iFind.find(customers);
    }
}

