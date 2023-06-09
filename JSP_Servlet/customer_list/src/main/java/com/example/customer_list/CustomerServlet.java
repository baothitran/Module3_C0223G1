package com.example.customer_list;

import model.Customer;
import model.CustomerType;
import service.CustomerService1;
import service.CustomerTypeService;
import service.ICustomerService;
import service.ICustomerTypeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService1 customerService1;
    private ICustomerTypeService iCustomerTypeService;
    List<Customer> customers;
    List<CustomerType> customerTypes;

    @Override
    public void init() throws ServletException {
        customerService1 = new CustomerService1();
        iCustomerTypeService = new CustomerTypeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreate(request, response);
                break;
            case "edit":
                showFormEdit(request, response);
                break;
            case "restore":
                showFormRestore(request,response);
            default:
                showCustomers(request, response);
        }
    }

    private void showFormRestore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customers = customerService1.getAllCustomersIsDeleted();
        req.setAttribute("customers",customers);
        req.getRequestDispatcher("/restore.jsp").forward(req,resp);
    }

    private void showCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customers = customerService1.getAllCustomers();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService1.findById(id);
        req.setAttribute("customer", customer);
        customerTypes = iCustomerTypeService.getAllCustomerType();
        req.setAttribute("customerTypes", customerTypes);
        req.getRequestDispatcher("/edit.jsp").forward(req,resp);
    }
    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req,resp);
    }


    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == "null") {
            action = "";
        }
        switch (action) {
            case "create":
                createCustomer(request, response);
                break;
            case "edit":
                editCustomer(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            case "restore":
                restoreCustomer(request,response);
                break;
            default:
                break;

        }
    }



    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService1.findById(id);

        String newName = req.getParameter("name");
        String newEmail = req.getParameter("email");
        String newAddress = req.getParameter("address");
        int newTypeId = Integer.parseInt(req.getParameter("typeId"));
        customer.setName(newName);
        customer.setEmail(newEmail);
        customer.setAddress(newAddress);
        customer.setTypeId(newTypeId);

        customerService1.updateCustomer(customer);
        resp.sendRedirect("/customer");
    }
    public void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        int id = (int) (System.currentTimeMillis()%10000);

        Customer customer = new Customer(id, name, email, address);

        customerService1.save(customer);

//        req.setAttribute("message","Thêm thành công");
//        req.getRequestDispatcher("/create.jsp").forward(req,resp);
        resp.sendRedirect("/customer");
    }
    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idEdit"));

        customerService1.delete(id);

//        customers = customerService.getAllCustomers();
        resp.sendRedirect("/customer");
    }
    private void restoreCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idEdit"));
        customerService1.restore(id);
        resp.sendRedirect("/customer");
    }
}
