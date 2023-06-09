package service;

import model.Customer;
import model.CustomerType;

import java.util.List;

public interface ICustomerTypeService {
    List<CustomerType> getAllCustomerType();
    CustomerType findById(int id);
}
