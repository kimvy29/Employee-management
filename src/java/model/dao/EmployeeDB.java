/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Account;
import model.entity.Contract;
import model.entity.Employee;

/**
 *
 * @author NguyenVy
 */
public class EmployeeDB implements DBContext {

    public static ArrayList<Employee> getAllEmployee() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT e.id,e.fullName,e.email,e.address, e.tel,e.positionId, p.name, e.managerId, e.activity, e.departmentId from Employee e\n"
                    + "INNER JOIN Position p ON e.positionId = p.id";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Employee> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getInt(10)));

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
            String query = "SELECT e.id,e.fullName,e.email,e.address, e.tel,e.positionId, p.name, e.managerId, e.activity, e.departmentId from Employee e\n"
                    + "INNER JOIN Position p ON e.positionId = p.id\n"
                    + "WHERE e.id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getInt(10));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.EmployeeDB.getEmployee()");
            throw new RuntimeException("Somthing error...");
        }
        throw new RuntimeException("Nhân viên không tồn tại!");
    }

    public static void create(Employee e, Contract c) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO Employee(fullName, email, address, tel, positionId, managerId, departmentId)\n"
                    + "OUTPUT inserted.id\n"
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, e.getFullName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getAddress());
            ps.setString(4, e.getTel());
            ps.setInt(5, e.getPositionId());
            if(e.getManagerId() == 0) {
                ps.setString(6, null);
            } else {
                ps.setInt(6, e.getManagerId());
            }
            ps.setInt(7, e.getDepartmentId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int empId = rs.getInt(1);
                Account a = new Account();
                a.setEmpId(empId);
                if(e.getPositionId() == 4){
                    a.setRoleId(3);
                } else {
                    a.setRoleId(2);
                }
                c.setEmpID(empId);
                c.create();
                a.setUserName(Characters.abbreviation(e.getFullName())+""+empId);
                a.create();
            }
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error as model.dao.EmployeeDB.create()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
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
            if(e.getManagerId() == 0) {
                ps.setString(6, null);
            } else {
                ps.setInt(6, e.getManagerId());
            }
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
        
        new Employee("Hồ Tấn Thành Nhân", "nhan@gmail.com", "Thừa Thiên Huế", "0349128669", 2, 0, 1).create(new Contract(Date.valueOf("2024-10-20"), 10000000, "Hỗ trợ xăng xe"));
//for(Employee e : getAllEmployee()){
//    System.out.println(e.getUserName());
//}
//System.out.println(new Contract(Date.valueOf("2024-10-20"), 10000000, "Hỗ trợ xăng xe"));
    }
}
