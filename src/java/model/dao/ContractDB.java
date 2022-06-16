/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.DBConnect.DBContext;
import model.entity.Contract;

/**
 *
 * @author LinhDen
 */
public class ContractDB {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    public List<Contract> getAllContract() throws Exception {
        List<Contract> list = new ArrayList<>();
        String SELECT_ALL_CONTRACT = "Select Contract.id, Contract.employeeID, Contract.fromDate, Contract.toDate, \n" +
                                     "Contract.salaryBasic, Contract.note, Employee.fullName  \n" +
                                     "from Employee inner join Contract on Employee.id = Contract.employeeId";

        try {
            Connection conn = DBContext.getConnection();
            System.out.println(conn);
            pstmt = conn.prepareStatement(SELECT_ALL_CONTRACT);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Contract (
                        rs.getInt("id"),
                        rs.getInt("empID"),
                        new Date(rs.getDate("fDate").getTime()),
                        new Date(rs.getDate("tDate").getTime()),
                        rs.getFloat("salaryBasic"),
                        rs.getString("note"),
                        rs.getString("empName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public Contract GetContractById(int id) throws Exception
    {
        String SELECT_CONTRACT = "SELECT * FROM Contract WHERE id = ?";

        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_CONTRACT);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Contract(
                        rs.getInt("id"),
                        rs.getInt("empID"),
                        new Date(rs.getDate("fDate").getTime()),
                        new Date(rs.getDate("tDate").getTime()),
                        rs.getFloat("salaryBasic"),
                        rs.getString("note"),
                        rs.getString("empName")
                );
            }
            conn.close();
            pstmt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
