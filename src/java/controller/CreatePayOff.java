/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.EmployeeDB;
import model.entity.Account;
import model.entity.Employee;
import model.entity.PayOff;

/**
 *
 * @author ACER
 */
public class CreatePayOff extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreatePayOff</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreatePayOff at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Account a = (Account) request.getSession().getAttribute("acc");
            if (a != null) {
                int type = a.getRoleId();
                switch (type) {
                    case 1:{
                        response.sendRedirect("list-employee");
                        break;
                    }
                    case 2: {
                        request.setAttribute("employee", EmployeeDB.getAllEmployeeByManagerId(a.getEmpId()));
                        request.getRequestDispatcher("CreatePayOff.jsp").forward(request, response);
                        break;
                    }
                    case 3: {
                        response.sendRedirect("list-employee");
                        break;
                    }
                }
            } else {
                response.sendRedirect("login");
            }
        } catch (IOException e) {
            response.sendRedirect("login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int empId = Integer.parseInt(request.getParameter("employeeId"));
        boolean payOff = Boolean.parseBoolean(request.getParameter("payOff"));
        String note = request.getParameter("note");
        long money = Long.parseLong(request.getParameter("money"));
        new PayOff(empId, payOff, note, money).create();
        response.sendRedirect("list-employee");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
