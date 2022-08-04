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
import model.entity.Account;
import model.entity.Employee;

/**
 *
 * @author ACER
 */
public class ForgotPassword extends HttpServlet implements Runnable {

    public ForgotPassword() {
    }
    
    

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
            out.println("<title>Servlet ForgotPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPassword at " + request.getContextPath() + "</h1>");
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
                response.sendRedirect("home");
            }
            request.getRequestDispatcher("ForgotPass.jsp").include(request, response);
        } catch (IOException | ServletException e) {
            request.getRequestDispatcher("ForgotPass.jsp").include(request, response);
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
        String userID = request.getParameter("userName");
        String email = request.getParameter("email");
        Account a = new Account(userID);
        Employee e = new Employee(a.getEmpId());
        if (e.getEmail().trim().equals(email.trim())) {
            ForgotPassword f = new ForgotPassword(a);
            f.start();
//            response.sendRedirect("login");
            request.getSession().setAttribute("count", 1);
            request.getSession().setAttribute("noti", "Mật khẩu của bạn được reset thành công, vui lòng chờ trong giây lát và kiểm tra email để đăng nhập lại!");
            response.sendRedirect("login");
        } else {
            throw new RuntimeException("Email không đúng!");
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

    private Thread t;
    private Account a;

    ForgotPassword(Account a) {
        this.a = a;
    }

    @Override
    public void run() {
        try {
            a.reset();
            Thread.sleep(50);

        } catch (InterruptedException e) {
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(new ForgotPassword(a));
            t.start();
        }
    }


}
