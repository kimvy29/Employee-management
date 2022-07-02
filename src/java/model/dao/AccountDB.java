/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Account;

/**
 *
 * @author ACER
 */
public class AccountDB implements DBContext {

    public static Account getAccount(String userName) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT userName, password, empId, roleId FROM Account WHERE userName = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                return a;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.getAccount()");
        }
        throw new RuntimeException("Tài khoản không tồn tại!");
    }
    
    public static Account getAccountByEmpId(int empId) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT userName, password, empId, roleId FROM Account WHERE empId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                return a;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.getAccountByEmpId()");
        }
        throw new RuntimeException("Tài khoản không tồn tại!");
    }

    public static void update(Account a){
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE Account\n"
                    + "set password = ?\n"
                    + "set roleId = ?\n"
                    + "where empId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getPassword());
            ps.setString(2, a.getUserName());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AccountDB.changPass()");
            throw new RuntimeException("Vui lòng thử lại!");
        }
    }

    public static void create(Account a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO Account(userName, empId, roleId)\n"
                    + "VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getUserName());
            ps.setInt(2, a.getEmpId());
            ps.setInt(3, a.getRoleId());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at AccountDB.create()");
            throw new RuntimeException("Vui lòng thử lại!");
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new Account(1005).getFullName());
    }
}
