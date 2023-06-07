package com.example.customer_list;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customer")
public class CustomerServlet extends HttpServlet {
    List<Customer> customers;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        customers = new ArrayList<>();
        customers.add(new Customer(1,"Thi","222","Huế"));
        customers.add(new Customer(2,"Thỉ","333","Quảng Trị"));
        customers.add(new Customer(3,"Bin","444","Đà Nẵng"));
        request.setAttribute("customers",customers);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
