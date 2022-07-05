/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.entity.Account;
import model.entity.Employee;

/**
 *
 * @author ACER
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 200,
        maxFileSize = 1024 * 1024 * 200,
        maxRequestSize = 1024 * 1024 * 200)
public class Profile extends HttpServlet {

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
            out.println("<title>Servlet Profile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Profile at " + request.getContextPath() + "</h1>");
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
                if (type != 1) {
                    request.setAttribute("employee", new Employee(a.getEmpId()));
                    request.getRequestDispatcher("Profile.jsp").include(request, response);
                } else {
                    response.sendRedirect("home");
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
        PrintWriter out = response.getWriter();
        Part avatar = request.getPart("avatar");
        String fileName = Paths.get(avatar.getSubmittedFileName()).getFileName().toString();
        if (fileName.toLowerCase().endsWith(".png") || fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg") || fileName.toLowerCase().endsWith(".gif")) {
            String real = request.getServletContext().getRealPath("./");
            String realPath = real.substring(0, real.indexOf("build")) + real.substring(real.indexOf("build") + 6) + "assets/imgs/avatar/" + a.getEmpId();
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            } 
            String buildPath = real + "assets/imgs/avatar/" + a.getEmpId();
            File fileBuild = new File(buildPath);
            if (!fileBuild.exists()) {
                fileBuild.mkdirs();
            } 
            SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
            java.util.Date date = new java.util.Date();
            avatar.write(realPath + "/" + f.format(date) + fileName);
            avatar.write(buildPath + "/" + f.format(date) + fileName);
            String path = "./assets/imgs/avatar/" + a.getEmpId() + "/" + f.format(date) + fileName;
            Employee e = new Employee(a.getEmpId());
            e.setAvatar(path);
            e.update();
            response.sendRedirect("profile");
        } else {
            throw new RuntimeException("Không đúng định dạng ảnh");
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
