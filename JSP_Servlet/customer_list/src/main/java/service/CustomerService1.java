package service;

import dao.CustomerServiceDAO;
import model.Customer;
import model.CustomerType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerService1 extends DBContext implements ICustomerService{
    private CustomerServiceDAO customerServiceDAO;
    private ICustomerTypeService iCustomerTypeService;
    public CustomerService1() {
        customerServiceDAO = new CustomerServiceDAO();
        iCustomerTypeService = new CustomerTypeService();
    }
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM luyentapjdbc.customer WHERE `deleted` = false;");
            System.out.println("Function getAllCustomers" + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date createAt = java.sql.Date.valueOf(rs.getString("createAt")) ;
                int typeId = rs.getInt("type_id");
                CustomerType customerType = iCustomerTypeService.findById(typeId);
                String typeName = customerType.getName();
                Customer customer = new Customer(id,name,email,address,createAt,typeId,typeName);
                customers.add(customer);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customers;
    }
    public List<Customer> getAllCustomersIsDeleted() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM luyentapjdbc.customer WHERE `deleted` = true;");
            System.out.println("Function getAllCustomers" + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date createAt = java.sql.Date.valueOf(rs.getString("createAt")) ;
                int typeId = rs.getInt("type_id");
                CustomerType customerType = iCustomerTypeService.findById(typeId);
                String typeName = customerType.getName();
                Customer customer = new Customer(id,name,email,address,createAt,typeId,typeName);
                customers.add(customer);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customers;
    }

    @Override
    public Customer findById(int id) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from luyentapjdbc.customer where id = ?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idCustomer = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date createAt = rs.getDate("createAt");
                Customer customer = new Customer(idCustomer,name,email,address,createAt);
                return customer;
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public List<Customer> searchCustomerByName(String name) {
        return customerServiceDAO.searchCustomerByName(name);
    }

    @Override
    public void updateCustomer(Customer customer1) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `luyentapjdbc`.`customer` SET `name` = ?, `email` = ?, `address` = ?, `createAt` = ?, `type_id` = ? WHERE (`id` = ?);");
            preparedStatement.setString(1,customer1.getName());
            preparedStatement.setString(2,customer1.getEmail());
            preparedStatement.setString(3,customer1.getAddress());
            preparedStatement.setDate(4,new java.sql.Date(customer1.getCreateAt().getTime()));
            preparedStatement.setInt(5,customer1.getTypeId());
            preparedStatement.setInt(6,customer1.getId());

            preparedStatement.executeUpdate();
            System.out.println("Function update" + preparedStatement);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public void delete(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `luyentapjdbc`.`customer` SET `deleted` = ? WHERE (`id` = ?);");
            preparedStatement.setBoolean(1,true);
            preparedStatement.setInt(2,id);
            System.out.println("function delete" + preparedStatement);
            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public void restore(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `luyentapjdbc`.`customer` SET `deleted` = ? WHERE (`id` = ?);");
            preparedStatement.setBoolean(1,false);
            preparedStatement.setInt(2,id);
            System.out.println("function delete" + preparedStatement);
            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public void save(Customer customer) {
        customerServiceDAO.save(customer);
    }
}
