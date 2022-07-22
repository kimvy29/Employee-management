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
import model.dao.DepartmentDB;
import model.dao.EmployeeDB;
import model.entity.Account;
import model.entity.Employee;

/**
 *
 * @author ACER
 */
public class UpdateEmployee extends HttpServlet {

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
            out.println("<title>Servlet UpdateEmployee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateEmployee at " + request.getContextPath() + "</h1>");
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
                int id = -1;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (Exception e) {
                    response.sendRedirect("list-employee");
                    return;
                }

                if (type == 1) {
                    request.setAttribute("e", new Employee(id));
                    request.setAttribute("manager", EmployeeDB.getAllManager());
                    request.setAttribute("department", DepartmentDB.getAllDepartment());
                    request.getRequestDispatcher("UpdateEmployee.jsp").include(request, response);
                } else {
                    response.sendRedirect("list-employee");
                }
            } else {
                response.sendRedirect("login");
            }
        } catch (IOException | ServletException e) {
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
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        int positionId = Integer.parseInt(request.getParameter("positionId"));
        int managerId = Integer.parseInt(request.getParameter("managerId"));
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        boolean sex = Boolean.parseBoolean(request.getParameter("sex"));
        Employee e = new Employee(id);
        e.setFullName(fullName);
        e.setEmail(email);
        e.setAddress(address);
        e.setTel(tel);
        e.setPositionId(positionId);
        e.setManagerId(managerId);
        e.setSex(sex);
        e.setDepartmentId(departmentId);
        e.update();
//        new Employee(id, fullName, email, address, tel, positionId, managerId, true, departmentId, sex).update();
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
