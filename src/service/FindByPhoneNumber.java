package service;

import entity.Customer;

import java.util.List;

public class FindByPhoneNumber implements IFind {
    @Override
    public void find(List<Customer> customers) {
        System.out.println("Danh sách số điện thoại của khách hàng:");
        for (Customer customer : customers) {
            System.out.println(customer.getPhoneNumber());
        }
    }
}
