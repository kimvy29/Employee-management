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
import model.entity.Department;
import model.entity.Employee;

/**
 *
 * @author ACER
 */
public class ChangeValueCreateEmployee extends HttpServlet {

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
            out.println("<title>Servlet ChangeValueCreateEmployee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeValueCreateEmployee at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        PrintWriter out = response.getWriter();
        int type = Integer.parseInt(request.getParameter("type"));
        int positionId = Integer.parseInt(request.getParameter("positionId"));
        if (type == 1) {
            if (positionId == 2) {
                for (Department d : DepartmentDB.getAllDepartmentNoManager()) {
                    out.print("<option value='" + d.getId() + "'>" + d.getName() + "</option>");
                }
                out.print("/////");
                out.print("<option value='" + 1001 + "'>Ceo</option>");
            } else if (positionId == 3) {
                for (Department d : DepartmentDB.getAllDepartment()) {
                    out.print("<option value='" + d.getId() + "'>" + d.getName() + "</option>");
                }
                out.print("/////");
                Department d = DepartmentDB.getAllDepartment().get(0);
                out.print("<option value='" + d.getManagerId() + "'>" + d.getManagerName() + "</option>");
            } else if (positionId == 4) {
                for (Department d : DepartmentDB.getAllDepartment()) {
                    out.print("<option value='" + d.getId() + "'>" + d.getName() + "</option>");
                }
                out.print("/////");
                Department d = DepartmentDB.getAllDepartment().get(0);
                out.print("<option value='0'>--------------------</option>");
                for (Employee e : EmployeeDB.getAllLeaderRoom(d.getId())) {
                    out.print("<option value='" + e.getId() + "'>" + e.getFullName() + "</option>");
                }
            }
        } else if (type == 2) {
            int departmentId = Integer.parseInt(request.getParameter("departmentId"));
            if (positionId == 2) {
                out.print("<option value='" + 1001 + "'>Ceo</option>");
            } else if(positionId == 3) {
                Department d = new Department(departmentId);
                out.print("<option value='" + d.getManagerId()+ "'>"+d.getManagerName()+"</option>");
            } else if(positionId == 4) {
                out.print("<option value='0'>--------------------</option>");
                for (Employee e : EmployeeDB.getAllLeaderRoom(departmentId)) {
                    out.print("<option value='" + e.getId() + "'>" + e.getFullName() + "</option>");
                }
            }
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
