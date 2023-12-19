package service;

import entity.Customer;

import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;

public class FindByName implements IFind {
    @Override
    public void find(List<Customer> customers) {
        System.out.println("Danh sách tên của khách hàng theo thứ tự ký tự alphabetic:");

        Collections.sort(customers, new Comparator<Customer>() {
            @Override
            public int compare(Customer customer1, Customer customer2) {
                String name1 = customer1.getUserName();
                String name2 = customer2.getUserName();
                return name1.compareTo(name2);
            }
        });

        for (Customer customer : customers) {
            System.out.println(customer.getUserName());
        }
    }
}
