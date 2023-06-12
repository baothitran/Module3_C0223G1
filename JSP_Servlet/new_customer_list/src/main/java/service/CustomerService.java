package service;

import model.Customer;
import model.CustomerType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerService extends DBContext implements ICustomerService {
    private ICustomerTypeService iCustomerTypeService;

    public CustomerService() {
        iCustomerTypeService = new CustomerTypeService();
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM luyentapjdbc.customer WHERE `deleted` = false;");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("function getAllCustomers(): " + preparedStatement);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date createAt = java.sql.Date.valueOf(rs.getString("createAt"));
                int typeId = rs.getInt("type_id");
                CustomerType customerType = iCustomerTypeService.findById(typeId);
                String typeName = customerType.getType();
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
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("function getAllCustomers(): " + preparedStatement);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date createAt = java.sql.Date.valueOf(rs.getString("createAt"));
                int typeId = rs.getInt("type_id");
                CustomerType customerType = iCustomerTypeService.findById(typeId);
                String typeName = customerType.getType();
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
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM luyentapjdbc.customer WHERE id = ?;");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("Funtion findById(int id): " + preparedStatement);
            while (rs.next()) {
                Customer customer = getCustomerFromRs(rs);
                return customer;
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }
    private Customer getCustomerFromRs(ResultSet rs) throws SQLException {
        int idCustomer = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String address = rs.getString("address");
        Date createAt = java.sql.Date.valueOf(rs.getString("createAt"));
        int typeId = rs.getInt("type_id");
        CustomerType customerType = iCustomerTypeService.findById(typeId);
        String typeName = customerType.getType();
        Customer customer = new Customer(idCustomer,name,email,address,createAt,typeId,typeName);
        return customer;
    }

    @Override
    public void update(Customer customer) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `luyentapjdbc`.`customer` SET `name` = ?, `email` = ?, `address` = ?, `createAt` = ?, `type_id` = ? WHERE (`id` = ?);");
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getEmail());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setDate(4, new java.sql.Date(customer.getCreateAt().getTime()));
        preparedStatement.setInt(5, customer.getTypeId());
        preparedStatement.setInt(6,customer.getId());
        preparedStatement.executeUpdate();
            System.out.println("Funtion update: " + preparedStatement);
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
            preparedStatement.executeUpdate();
            System.out.println("Funtion delete: " + preparedStatement);
        } catch (SQLException sqlException) {
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
            preparedStatement.executeUpdate();
            System.out.println("Funtion restore: " + preparedStatement);
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public void create(Customer customer) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `luyentapjdbc`.`customer` (`name`, `email`, `address`, `createAt`, `type_id`, `deleted`) VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setDate(4,new java.sql.Date(customer.getCreateAt().getTime()));
            preparedStatement.setInt(5,customer.getTypeId());
            preparedStatement.setBoolean(6,false);
            preparedStatement.executeUpdate();
            System.out.println("Function create: " + preparedStatement);

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
}