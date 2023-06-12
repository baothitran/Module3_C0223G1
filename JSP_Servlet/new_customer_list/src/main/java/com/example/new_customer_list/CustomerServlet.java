package com.example.new_customer_list;

import model.Customer;
import model.CustomerType;
import service.CustomerService;
import service.CustomerTypeService;
import service.ICustomerService;
import service.ICustomerTypeService;
import utils.ValidateUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customers")
public class CustomerServlet extends HttpServlet {
    private ICustomerService customerService;
    private ICustomerTypeService customerTypeService;
    List<Customer> customers;
    List<CustomerType> customerTypes;
    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
        customerTypeService = new CustomerTypeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreate(req,resp);
                break;
            case "update":
                showFormUpdate(req,resp);
            case "restore":
                showFormRestore(req, resp);
                break;
            default:
                showCustomers(req,resp);
        }
    }

    private void showFormUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.findById(id);
        req.setAttribute("customer",customer);
        customerTypes = customerTypeService.getAllCustomerType();
        req.setAttribute("customerTypes",customerTypes);
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }

    private void showCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customers = customerService.getAllCustomers();
        req.setAttribute("customers",customers);
        req.getRequestDispatcher("show.jsp").forward(req,resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customerTypes = customerTypeService.getAllCustomerType();
        req.setAttribute("customerTypes", customerTypes);
        req.getRequestDispatcher("create.jsp").forward(req,resp);
    }
    private void showFormRestore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customers = customerService.getAllCustomersIsDeleted();
        req.setAttribute("customers",customers);
        req.getRequestDispatcher("/restore.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == "null") {
            action = "";
        }
        switch (action) {
            case "create":
                createCustomer(req,resp);
                break;
            case "edit":
            case "delete":
                deleteCustomer(req, resp);
                break;
            case "restore":
                restoreCustomer(req, resp);
                break;

        }
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Customer customer = new Customer();
        validateInputName(req,errors,customer);
        validateInputEmail(req,errors,customer);
        validateInputCustomerType(req,errors,customer);

        String address = req.getParameter("address");
        long id = System.currentTimeMillis()%100000;
        customer.setCreateAt(new Date());
        customer.setAddress(address);
        customer.setId((int) id);


        if (errors.isEmpty()) {
            customerService.create(customer);
            req.setAttribute("message", "Thêm thành công");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("customer", customer);
        }
        customerTypes = customerTypeService.getAllCustomerType();
        req.setAttribute("customerTypes", customerTypes);
        customers = customerService.getAllCustomers();
        req.setAttribute("customers",customers);
        req.getRequestDispatcher("show.jsp").forward(req,resp);
    }
    private void restoreCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idEdit"));
        customerService.restore(id);
        customers = customerService.getAllCustomersIsDeleted();
        req.setAttribute("customers",customers);
        req.setAttribute("message", "Khôi phục thành công");
        req.getRequestDispatcher("restore.jsp").forward(req,resp);
    }
    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idEdit"));
        customerService.delete(id);
        req.setAttribute("message", "Xoá thành công");
        customers = customerService.getAllCustomers();
        req.setAttribute("customers",customers);
        req.getRequestDispatcher("show.jsp").forward(req,resp);
    }
    private void validateInputCustomerType(HttpServletRequest req, List<String> errors, Customer customer) {
        try {
            int customerType = Integer.parseInt(req.getParameter("typeId"));
            customer.setTypeId(customerType);
        } catch (NumberFormatException numberFormatException) {
            errors.add("Loại khách hàng không hợp lệ");
        }

    }

    private void validateInputEmail(HttpServletRequest req, List<String> errors, Customer customer) {
        String email = req.getParameter("email");
        if (!ValidateUtils.isEmailValid(email)) {
            errors.add("Email không hợp lệ");
        }
        customer.setEmail(email);
    }

    private void validateInputName(HttpServletRequest req, List<String> errors, Customer customer) {
        String name = req.getParameter("name");
        if (!ValidateUtils.isNameValid(name)) {
            errors.add("Tên không hợp lệ. Tên phải từ 8-20 kí tự và bắt đầu là chữ cái");
        }
        customer.setName(name);
    }

}
