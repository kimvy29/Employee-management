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
            String query = "SELECT id, employeeId, currentDate, salary, sumWorking, sumOver, sumPunish, sumBonus, sumPunishMoney FROM Salary WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Salary(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getLong(4),rs.getLong(5), rs.getLong(6), rs.getInt(7), rs.getLong(8), rs.getLong(9));
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
            String query = "SELECT id, employeeId, currentDate, salary, sumWorking, sumOver, sumPunish, sumBonus, sumPunishMoney FROM Salary WHERE employeeId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            ArrayList<Salary> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Salary(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getLong(4),rs.getLong(5), rs.getLong(6), rs.getInt(7), rs.getLong(8), rs.getLong(9)));
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at SalaryDB.getSalaryByEmployee()");
        }
        throw new RuntimeException("Bảng lương không tồn tại!");
    }
    
    public static ArrayList<Salary> getAllSalaryByManagerId(int managerId) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT s.id, s.employeeId, s.currentDate, s.salary, s.sumWorking, s.sumOver, s.sumPunish, s.sumBonus, s.sumPunishMoney FROM Salary s INNER JOIN Employee e on s.employeeId = e.id\n"
                    + "WHERE e.managerId = ?\n"
                    + "ORDER BY e.id";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();
            ArrayList<Salary> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Salary(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getLong(4),rs.getLong(5), rs.getLong(6), rs.getInt(7), rs.getLong(8), rs.getLong(9)));
            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.SalaryDB.getAllSalaryByManagerId()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static ArrayList<Salary> getAllSalary() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, employeeId, currentDate, salary, sumWorking, sumOver, sumPunish, sumBonus, sumPunishMoney FROM Salary";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Salary> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Salary(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getLong(4),rs.getLong(5), rs.getLong(6), rs.getInt(7), rs.getLong(8), rs.getLong(9)));
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
            String query = "INSERT INTO Salary(employeeId, salary, sumWorking, sumOver, sumPunish, sumBonus, sumPunishMoney)\n"
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, s.getEmpId());
            ps.setLong(2, s.getSalary());
            ps.setLong(3, s.getSumWorking());
            ps.setLong(4, s.getSumOver());
            ps.setInt(5, s.getSumPunish());
            ps.setLong(6, s.getSumBonus());
            ps.setLong(7, s.getSumPunishMoney());
            TimeKeepingDB.paySalary(s.getEmpId());
            PayOffDB.paySalary(s.getEmpId());
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
