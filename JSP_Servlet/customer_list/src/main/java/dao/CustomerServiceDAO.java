package dao;

import model.Customer;
import model.CustomerType;
import service.CustomerTypeService;
import service.ICustomerService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerServiceDAO implements ICustomerService {
    private String jdbcURL = "jdbc:mysql://localhost:3306/luyentapjdbc?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private CustomerTypeService customerTypeService;

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM luyentapjdbc.customer;");
            System.out.println("Function getAllCustomers" + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date createAt = java.sql.Date.valueOf(rs.getString("createAt")) ;
                int typeId = rs.getInt("type_id");
//                Customer customer = new Customer(id,name,email,address,createAt,typeId);
//                customers.add(customer);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customers;
    }

    @Override
    public List<Customer> getAllCustomersIsDeleted() {
        return null;
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
        return null;
    }

    @Override
    public void updateCustomer(Customer customer1) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `luyentapjdbc`.`customer` SET `name` = ?, `email` = ?, `address` = ?, `createAt` = ? WHERE (`id` = ?);");
            preparedStatement.setString(1,customer1.getName());
            preparedStatement.setString(2,customer1.getEmail());
            preparedStatement.setString(3,customer1.getAddress());
            preparedStatement.setDate(4,new java.sql.Date(customer1.getCreateAt().getTime()));
            preparedStatement.setInt(5,customer1.getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `luyentapjdbc`.`customer` WHERE (`id` = ?);");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

    }

    @Override
    public void restore(int id) {

    }

    @Override
    public void save(Customer customer) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `luyentapjdbc`.`customer` (`name`, `email`, `address`, `createAt`) VALUES (?, ?,?, ?);");
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getEmail());
            preparedStatement.setString(3,customer.getAddress());
            preparedStatement.setDate(4,new java.sql.Date(customer.getCreateAt().getTime()));

            System.out.println("Function save" + preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

    }
}
