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
import model.entity.Salary;

/**
 *
 * @author ACER
 */
public class SalaryDB implements DBContext {

    public static Salary getSalary(int id) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, employeeId, currentDate, salary FROM Salary WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Salary(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getLong(4));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at SalaryDB.getSalary()");
        }
        throw new RuntimeException("Bảng lương không tồn tại!");
    }

    public static ArrayList<Salary> getSalaryByEmployee(int empId) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, employeeId, currentDate, salary FROM Salary WHERE employeeId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            ArrayList<Salary> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Salary(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getLong(4)));
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at SalaryDB.getSalaryByEmployee()");
        }
        throw new RuntimeException("Bảng lương không tồn tại!");
    }

    public static ArrayList<Salary> getAllSalary() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, employeeId, currentDate, salary FROM Salary";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Salary> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Salary(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getLong(4)));
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at SalaryDB.getAllSalary()");
        }
        throw new RuntimeException("Bảng lương không tồn tại!");
    }

    public static void create(Salary s) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO Salary(employeeId, salary)\n"
                    + "VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, s.getEmpId());
            ps.setLong(2, s.getSalary());
            TimeKeepingDB.paySalary(s.getEmpId());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at SalaryDB.create()");
            throw new RuntimeException("Có lỗi xảy ra!");
        }
        
    }
}
