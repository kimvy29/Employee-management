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

    public Account() {
    }

    public Account(String userName, String password, int empId, int roleId) {
        this.userName = userName;
        this.password = password;
        this.empId = empId;
        this.roleId = roleId;
    }
    
    public Account(Account a){
        this(a.userName, a.password, a.empId, a.roleId);
    }
    
    public Account(String userName) {
        this(AccountDB.getAccount(userName));
    }
    
    public Account(int empId) {
        this(AccountDB.getAccountByEmpId(empId));
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

    public static Account login(String userID, String password) {
        Account a = AccountDB.getAccount(userID);
        if (a.isActivity()) {
            if (a.getPassword().equals(Account.pass(password))) {
            return a;
        } else {
            throw new RuntimeException("Mật khẩu sai!");
        }
        } else {
            throw new RuntimeException("Tài khoản bị khóa");
        }
        
    }
    
    public void changePass(String oldPass, String newPass) {
        if (this.password.equals(Account.pass(oldPass))) {
            this.setPassword(Account.pass(newPass));
            AccountDB.update(this);
        } else {
            throw new RuntimeException("Mật khẩu sai!");
        }
    }
    
    public boolean checkPass(String password) {
        if (this.password.equals(Account.pass(password))) {
            return true;
        }
        throw new RuntimeException("Mật khẩu sai!");
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmpId() {
        return empId;
    }
    
    public String getFullName() {
        return new Employee(this.empId).getFullName();
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
        return new Employee(this.empId).isActivity();
    }
    
    public String getAvatar() {
        return new Employee(empId).getAvatar();
    }

    @Override
    public String toString() {
        return "Account{" + "userName=" + userName + ", password=" + password + ", empId=" + empId + ", roleId=" + roleId + '}';
    }
    
    public void update(){
        AccountDB.update(this);
    }
    
    public void create(){
        AccountDB.create(this);
    }
    
    public static void main(String[] args) {
        System.out.println(pass("admin"));
    }
}
