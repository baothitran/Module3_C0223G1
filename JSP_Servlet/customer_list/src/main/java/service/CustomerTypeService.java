package service;

import model.Customer;
import model.CustomerType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class CustomerTypeService extends DBContext implements ICustomerTypeService {

    @Override
    public List<CustomerType> getAllCustomerType() {
        List<CustomerType> customerTypes = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM luyentapjdbc.customer_type;");
            System.out.println("Function getAllCustomerType: "+ preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CustomerType customerType = getCustomerTypeFromRs(rs);
                customerTypes.add(customerType);

            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return customerTypes;
    }
    private CustomerType getCustomerTypeFromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Date sqlDeleteAt = rs.getDate("delete_at");
        LocalDate lDeleteAt = null;
        if (sqlDeleteAt != null) {
            lDeleteAt = sqlDeleteAt.toLocalDate();
        }
        CustomerType customerType = new CustomerType(id,name,lDeleteAt);
        return customerType;
    }

    @Override
    public CustomerType findById(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM luyentapjdbc.customer_type WHERE id = ?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CustomerType customerType = getCustomerTypeFromRs(rs);
                return customerType;
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }
}
