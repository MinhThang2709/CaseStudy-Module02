package abstraction;

import entity.Address;

import java.io.Serializable;

public class User implements Serializable {
    private int ID;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private static int count = 0;
    Address address;

    public User() {
    }

    protected User(String username, String phoneNumber, String email, String password, Address address) {
        this.ID = ++count;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.address = address;

    }

    protected User(String phoneNumber, String email, String address) {
        this.ID = ++count;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    protected User(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "THÔNG TIN NGƯỜI DÙNG: "
                + "\n" + "- ID: " + ID
                + "\n" + "- Số điện thoại: " + phoneNumber
                + "\n" + "- Email: " + email
                + "\n" + "- Địa chỉ nhà: " + address;
    }

}
