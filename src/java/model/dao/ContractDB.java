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
import java.util.Date;
import model.DBConnect.DBContext;
import model.entity.Contract;

/**
 *
 * @author LinhDen
 */
public class ContractDB implements DBContext{
    public static ArrayList<Contract> getAllContract() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "Select Contract.id, Contract.employeeID, Contract.fromDate, Contract.toDate, \n" +
                           "Contract.salaryBasic, Contract.note, Employee.fullName  \n" +
                           "from Employee inner join Contract on Employee.id = Contract.employeeId ";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Contract> list = new ArrayList<>();
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                list.add(new Contract(rs.getInt(1),
                        rs.getInt(2),
                        new Date(rs.getDate(3).getTime()),
                        new Date(rs.getDate(4).getTime()),
                        rs.getFloat(5),
                        rs.getString(6),
                        rs.getString(7)));

            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.ContractDB.getAllContract()");
            throw new RuntimeException("Somthing error...");
        }
    }
    //public static void main(String[] args) {
    //    for(Contract e: ContractDB.getAllContract()){
    //        System.out.println(e);
    //   }
    //    System.out.println(ContractDB.getAllContract());
    //}
    

public static Contract GetContractById(int id) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "Select Contract.id, Contract.employeeID, Contract.fromDate, Contract.toDate, \n" +
                           "Contract.salaryBasic, Contract.note, Employee.fullName  \n" +
                           "from Employee inner join Contract on Employee.id = Contract.employeeId where Contract.id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (rs.next()) {
                return new Contract(rs.getInt(1),
                        rs.getInt(2),
                        new Date(rs.getDate(3).getTime()),
                        new Date(rs.getDate(4).getTime()),
                        rs.getFloat(5),
                        rs.getString(6),
                        rs.getString(7));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.ContractDB.getAllContract()");
            throw new RuntimeException("Somthing error...");
        }
        throw new RuntimeException("Hợp Đồng không tồn tại!");
    }
}
