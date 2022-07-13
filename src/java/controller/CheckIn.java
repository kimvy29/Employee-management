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
import model.dao.TimeKeepingDB;
import model.entity.Account;
import model.entity.Employee;
import model.entity.TimeKeeping;

/**
 *
 * @author ACER
 */
public class CheckIn extends HttpServlet {

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
            out.println("<title>Servlet CheckIn</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckIn at " + request.getContextPath() + "</h1>");
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
                    case 1: {
                        response.sendRedirect("home");
                        break;
                    }
                    case 2:
                    case 3: {
                        if (a.getPositionId() == 1) {
                            response.sendRedirect("home");
                            break;
                        } else {
                            request.setAttribute("current", TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(a.getEmpId())));
                            request.setAttribute("list", TimeKeepingDB.getTimeKeepingByEmployee(new Employee(a.getEmpId())));
                            request.getRequestDispatcher("CheckIn.jsp").include(request, response);
                            break;
                        }
                    }
                    default:
                        response.sendRedirect("home");
                        break;
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
        Account a = (Account) request.getSession().getAttribute("acc");
        int id = a.getEmpId();
        String action = request.getParameter("action");
        switch (action) {
            case "Check-in":
                new Employee(id).startTime();
                break;
            case "Check-out":
                new TimeKeeping(TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)).getId()).endTime();
                break;
            case "Check-in-overtime":
                new TimeKeeping(TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)).getId()).startOverTime();
                break;
            case "Check-out-overtime":
                new TimeKeeping(TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)).getId()).endOverTime();
                break;
        }
        response.sendRedirect("check-in");
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
