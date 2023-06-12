package service;

import model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();
    Customer findById(int id);
    void update(Customer customer);
    void delete(int id);
    void restore(int id);
    void create(Customer customer);

    List<Customer> getAllCustomersIsDeleted();
}
