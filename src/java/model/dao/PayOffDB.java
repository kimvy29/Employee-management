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
import model.entity.Employee;
import model.entity.PayOff;

/**
 *
 * @author ACER
 */
public class PayOffDB implements DBContext {

    public static PayOff getPayOff(int id) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, employeeId, currentDate, payoff, note, money, checkPay FROM Payoff where id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
//            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            if (rs.next()) {
                return new PayOff(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getBoolean(4), rs.getString(5), rs.getLong(6), rs.getBoolean(7));

            }
            conn.close();
            return null;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.PayOffDB.getPayOff()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static ArrayList<PayOff> getPayOffByEmployee(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, employeeId, currentDate, payoff, note, money, checkPay FROM Payoff where checkPay = 0 and employeeId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, e.getId());
            ResultSet rs = ps.executeQuery();
            ArrayList<PayOff> list = new ArrayList<>();
//            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            while (rs.next()) {
                list.add(new PayOff(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getBoolean(4), rs.getString(5), rs.getLong(6), rs.getBoolean(7)));

            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.PayOffDB.getPayOffByEmployee()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static void createPayOff(PayOff p) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO Payoff(employeeId, payoff, note, money)\n"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            ps.setInt(1, p.getEmpId());
            ps.setBoolean(2, p.isPayoff());
            ps.setString(3, p.getNote());
            ps.setLong(4, p.getMoney());
            java.time.LocalDateTime d = java.time.LocalDateTime.now();
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.ContractDB.startDate()");
            throw new RuntimeException("Somthing error...");
        }
    }
}
