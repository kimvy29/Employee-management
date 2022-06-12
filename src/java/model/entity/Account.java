/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import model.dao.AccountDB;

/**
 *
 * @author ACER
 */
public class Account {
    private String userName;
    private String password;
    private int empId;
    private int roleId;
    private boolean activity;

    public Account() {
    }

    public Account(String userName, String password, int empId, int roleId, boolean activity) {
        this.userName = userName;
        this.password = password;
        this.empId = empId;
        this.roleId = roleId;
        this.activity = activity;
    }
    
    public Account(Account a){
        this(a.userName, a.password, a.empId, a.roleId, a.activity);
    }
    
    public Account(String userName) {
        this(AccountDB.getAccount(userName));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    
    public static String pass(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // replay MD5, SHA-512
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            password = DatatypeConverter.printHexBinary(hash);
            return password;
        } catch (NoSuchAlgorithmException e) {
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Account{" + "userName=" + userName + ", password=" + password + ", empNo=" + empId + ", roleId=" + roleId + ", activity=" + activity + '}';
    }

    public static Account login(String userName, String password) {
        return null;
    }
}
