package service;

import model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();

    List<Customer> getAllCustomersIsDeleted();
    Customer findById(int id);
    List<Customer> searchCustomerByName(String name);
    void updateCustomer(Customer customer1);
    void delete(int id);
    void restore(int id);
    void save(Customer customer);
}
