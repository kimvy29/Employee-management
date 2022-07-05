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
import java.sql.Date;
import model.DBConnect.DBContext;
import model.entity.Contract;
import model.entity.Employee;

/**
 *
 * @author LinhDen
 */
public class ContractDB implements DBContext {

    public static ArrayList<Contract> getAllContract() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "Select id, employeeId, fromDate, toDate, salaryBasic, note FROM Contract";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Contract> list = new ArrayList<>();
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                list.add(new Contract(rs.getInt(1), rs.getInt(2), java.sql.Date.valueOf(rs.getString(3)), java.sql.Date.valueOf(rs.getString(4)), rs.getLong(5), rs.getString(6)));

            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.ContractDB.getAllContract()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static Contract GetContractById(int id) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "Select id, employeeId, fromDate, toDate, salaryBasic, note FROM Contract WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (rs.next()) {
                return new Contract(rs.getInt(1), rs.getInt(2), java.sql.Date.valueOf(rs.getString(3)), java.sql.Date.valueOf(rs.getString(4)), rs.getLong(5), rs.getString(6));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.ContractDB.GetContractById()");
            throw new RuntimeException("Somthing error...");
        }
        throw new RuntimeException("Hợp Đồng không tồn tại!");
    }

    public static Contract GetContractByEmployeeId(int id) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "Select id, employeeId, fromDate, toDate, salaryBasic, note FROM Contract WHERE employeeId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (rs.next()) {
                return new Contract(rs.getInt(1), rs.getInt(2), java.sql.Date.valueOf(rs.getString(3)), java.sql.Date.valueOf(rs.getString(4)), rs.getLong(5), rs.getString(6));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.ContractDB.GetContractByEmployeeId()");
            throw new RuntimeException("Somthing error...");
        }
        throw new RuntimeException("Hợp Đồng không tồn tại!");
    }

    public static void create(Contract c) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO Contract(employeeId, toDate, salaryBasic, note)\n"
                    + "VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            ps.setInt(1, c.getEmpId());
            ps.setDate(2, c.getTDate());
            ps.setLong(3, c.getSalaryBasic());
            ps.setString(4, c.getNote());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.ContractDB.create()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static void update(Contract c) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE Contract\n"
                    + "SET toDate = ?, salaryBasic = ?, note = ?\n"
                    + "WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1, c.getTDate());
            ps.setLong(2, c.getSalaryBasic());
            ps.setString(3, c.getNote());
            ps.setInt(4, c.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.ContractDB.update()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static void main(String[] args) {
        new Contract(8, Date.valueOf("2025-10-10"), 20000000, "ok").update();
    }
}
