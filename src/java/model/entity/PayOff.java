/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.dao.PayOffDB;

/**
 *
 * @author ACER
 */
public class PayOff {
    private int id;
    private int empId;
    private Date currentDate;
    private boolean payoff;
    private String note;
    private long money;
    private boolean checkPay;

    public PayOff() {
    }

    public PayOff(int id, int empId, Date currentDate, boolean payoff, String note, long money, boolean checkPay) {
        this.id = id;
        this.empId = empId;
        this.currentDate = currentDate;
        this.payoff = payoff;
        this.note = note;
        this.money = money;
        this.checkPay = checkPay;
    }

    public PayOff(int empId, boolean payoff, String note, long money) {
        this.empId = empId;
        this.payoff = payoff;
        this.note = note;
        this.money = money;
    }
    
    public PayOff(PayOff p) {
        this(p.id, p.empId, p.currentDate, p.payoff, p.note, p.money, p.checkPay);
    }
    
    public PayOff(int id) {
        this(PayOffDB.getPayOff(id));
    }

    public static ArrayList<PayOff> listPayOff(Employee e) {
        return PayOffDB.getPayOffByEmployee(e);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getCurrentDate() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        return f.format(currentDate);
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public boolean isPayoff() {
        return payoff;
    }

    public String getStatus(){
        if(this.isPayoff()) {
            return "Thưởng";
        } else {
            return "Phạt";
        }
    }
    
    public void setPayoff(boolean payoff) {
        this.payoff = payoff;
    }
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public boolean isCheckPay() {
        return checkPay;
    }

    public void setCheckPay(boolean checkPay) {
        this.checkPay = checkPay;
    }

    @Override
    public String toString() {
        return "PayOff{" + "id=" + id + ", empId=" + empId + ", currentDate=" + currentDate + ", payoff=" + payoff + ", note=" + note + ", money=" + money + ", checkPay=" + checkPay + '}';
    }
    
    public void create() {
        PayOffDB.createPayOff(this);
    }
}
