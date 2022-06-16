/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.DBConnect.DBContext;
import model.entity.Account;

/**
 *
 * @author ACER
 */
public class AccountDB implements DBContext {

    public static Account getAccount(String userID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT A.userName, A.password, A.empId, A.roleId, E.activity\n"
                    + "FROM Account A INNER JOIN Employee E ON A.empId = E.id";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5));
                return a;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.getAccount()");
        }
        throw new RuntimeException("Tài khoản không tồn tại!");
    }
    
    public static Account checkAccount(String userName, String password){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;   
        String sql = "select A.userName, A.password, A.empId, A.roleId, E.activity"
                    + "from Account A inner join Employee E on A.empId = E.id"
                    + "where A.userName=? and A.password=? and E.activate = 1";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5));
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!" + e.getMessage());
        }
        throw new RuntimeException("Tài khoản đã bị khóa!");
    }
//
//    public static void doChangePass(Account a){
//        try (Connection conn = DBContext.getConnection()) {
//            String query = "update Account "
//                    + "set password = ? "
//                    + "where userID = ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setString(1, password);
//            ps.setString(2, userID);
//            ps.executeUpdate();
//            conn.close();
//        } catch (Exception e) {
//            System.out.println(e);
//            System.out.println("Error at AccountDB.changPass()");
//            throw new RuntimeException("Vui lòng thử lại!");
//        }
//    }
    
    public boolean doChangePass(String userID, String newPass) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;   
        String sql = "update [Account] set password = ? where userID = ?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPass);
            ps.setString(2, userID);
            if (ps.executeUpdate()>0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("change pass " + e.getMessage());
        }
        return false;
    }
  
}
