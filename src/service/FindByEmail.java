package service;

import entity.Customer;

import java.util.List;

public class FindByEmail implements IFind {
    @Override
    public void find(List<Customer> customers) {
        System.out.println("Danh sách Email của khách hàng:");
        for (Customer customer : customers) {
            System.out.println(customer.getEmail());
        }
    }
}
