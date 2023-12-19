package factory;

import abstraction.User;
import entity.Address;
import entity.Customer;

public class UserFactory {
    private static final UserFactory userFactory = new UserFactory();
    private UserFactory(){}
    public static UserFactory getInstance(){
        return userFactory;
    }
    public User getUser(String userName, String phoneNumber, String email, String passWord, Address address, String typeUser) {
        String typeUserToUpperCase = typeUser.toUpperCase();
        return switch (typeUserToUpperCase) {
            case "CUSTOMER" -> new Customer(userName, phoneNumber, email, passWord, address);
            default -> null;
        };
    }
}
