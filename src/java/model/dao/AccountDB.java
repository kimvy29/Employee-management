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
    
    
}
