/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.EmployeeDB;
import model.entity.Account;
import model.entity.Contract;
import model.entity.Employee;

/**
 *
 * @author ACER
 */
public class ContractDetail extends HttpServlet {

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
            out.println("<title>Servlet ContractDetail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContractDetail at " + request.getContextPath() + "</h1>");
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

                switch(type){
                    case 1: {
                        request.setAttribute("contract", new Contract(new Employee(id)));
                        request.getRequestDispatcher("ContractDetail.jsp").include(request, response);
                        break;
                    }
                    case 2: {
                        if(new Employee(id).getManagerId() != a.getEmpId() && id != a.getEmpId()) {
                            response.sendRedirect("list-employee");
                        } else {
                            request.setAttribute("contract", new Contract(new Employee(id)));
                            request.getRequestDispatcher("ContractDetail.jsp").include(request, response);
                        }
                        break;
                    }
                    case 3: {
                        if(id != a.getEmpId()) {
                            response.sendRedirect("list-employee");
                        } else {
                            request.setAttribute("contract", new Contract(new Employee(id)));
                            request.getRequestDispatcher("ContractDetail.jsp").include(request, response);
                        }
                        break;
                    }
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
        if (a.getRoleId() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            Date toDate = Date.valueOf(request.getParameter("toDate"));
            Long salaryBasic = Long.parseLong(request.getParameter("salaryBasic"));
            String note = request.getParameter("note");
            new Contract(id, toDate, salaryBasic, note).update();
        } else {
            response.sendRedirect("home");
        }
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
