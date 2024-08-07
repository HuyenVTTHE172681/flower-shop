package com.flowerstore.controller;

import com.flowerstore.dao.AdminDAO;
import com.flowerstore.model.Admin;
import com.flowerstore.model.option.AdminRole;
import com.flowerstore.service.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAccountAdminController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Admin admin = new Admin();
            admin.setUserName(username);
            admin.setPassword(Hash.hashCode(password));
            admin.setRole(AdminRole.create("Admin"));
            AdminDAO.getInstance().insert(admin);
            String url = request.getHeader("referer");
            response.sendRedirect(url);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    }


}
