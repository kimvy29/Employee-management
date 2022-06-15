/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Employee;

/**
 *
 * @author NguyenVy
 */
public class EmployeeDB implements DBContext {

    public static ArrayList<Employee> getAllEmployee() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "select e.id,e.fullName,e.email,e.address, e.tel,e.positionId,p.name as positionName, e.managerId,e2.fullName as managerName, e.activity,e.departmentId,d.name as departmentName from employee e\n"
                    + "INNER JOIN Position p ON e.positionId = p.id\n"
                    + "INNER JOIN Employee e2 ON e.managerId = e2.id\n"
                    + "INNER JOIN Department d ON e.departmentId = d.id";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Employee> list = new ArrayList<>();
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                list.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getBoolean(10), rs.getInt(11), rs.getString(12)));

            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.EmployeeDB.getAllEmployee()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static Employee getEmployee(int id) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "select e.id,e.fullName,e.email,e.address, e.tel,e.positionId,p.name as positionName, e.managerId,e2.fullName as managerName, e.activity,e.departmentId,d.name as departmentName from employee e\n"
                    + "INNER JOIN Position p ON e.positionId = p.id\n"
                    + "INNER JOIN Employee e2 ON e.managerId = e2.id\n"
                    + "INNER JOIN Department d ON e.departmentId = d.id\n"
                    + "WHERE e.id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (rs.next()) {
                return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getBoolean(10), rs.getInt(11), rs.getString(12));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.EmployeeDB.getEmployee()");
            throw new RuntimeException("Somthing error...");
        }
        throw new RuntimeException("Nhân viên không tồn tại!");
    }

    public static void update(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE Employee\n"
                    + "SET fullName = ?, email = ?, address = ?, tel = ?, positionId = ?, managerId = ?, activity = ?, departmentId = ?\n"
                    + "WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, e.getFullName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getAddress());
            ps.setString(4, e.getTel());
            ps.setInt(5, e.getPositionId());
            ps.setInt(6, e.getManagerId());
            ps.setBoolean(7, e.isActivity());
            ps.setInt(8, e.getDepartmentId());
            ps.setInt(9, e.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error as model.dao.EmployeeDB.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
    
    public static void delete(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE Employee\n"
                    + "WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error as model.dao.EmployeeDB.delete()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void main(String[] args) {
//        for(Employee e: EmployeeDB.getAllEmployee()){
//            System.out.println(e);
//        }
        System.out.println(EmployeeDB.getEmployee(1));
    }
}
