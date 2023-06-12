package service;

import model.CustomerType;

import java.util.List;

public interface ICustomerTypeService {
    public List<CustomerType> getAllCustomerType();
    CustomerType findById(int id);
}
