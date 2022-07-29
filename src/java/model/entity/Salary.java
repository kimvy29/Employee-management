/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import model.dao.SalaryDB;

/**
 *
 * @author ACER
 */
public class Salary {
    private int id;
    private int empId;
    private Date curentDate;
    private long salary;
    private float sumWorking;
    private float sumOver;
    private int sumPunish;
    private long sumBonus;
    private long sumPunishMoney;

    public Salary() {
    }

    public Salary(int empId, long salary, float sumWorking, float sumOver, int sumPunish, long sumBonus, long sumPunishMoney) {
        this.empId = empId;
        this.salary = salary;
        this.sumWorking = sumWorking;
        this.sumOver = sumOver;
        this.sumPunish = sumPunish;
        this.sumBonus = sumBonus;
        this.sumPunishMoney = sumPunishMoney;
    }

    public Salary(int id, int empId, Date curentDate, long salary, float sumWorking, float sumOver, int sumPunish, long sumBonus, long sumPunishMoney) {
        this.id = id;
        this.empId = empId;
        this.curentDate = curentDate;
        this.salary = salary;
        this.sumWorking = sumWorking;
        this.sumOver = sumOver;
        this.sumPunish = sumPunish;
        this.sumBonus = sumBonus;
        this.sumPunishMoney = sumPunishMoney;
    }
    
    public Salary(int id) {
        this(SalaryDB.getSalary(id));
    }
    
    public Salary(Salary s) {
        this(s.id, s.empId, s.curentDate, s.salary, s.sumWorking, s.sumOver, s.sumPunish, s.sumBonus, s.sumPunishMoney);
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
    
    public String getFullName() {
        return new Employee(empId).getFullName();
    }
    
    public String getAccount() {
        return new Employee(empId).getUserName();
    }

    public String getCurentDate() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        return f.format(curentDate);
    }

    public void setCurentDate(Date curentDate) {
        this.curentDate = curentDate;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public float getSumWorking() {
        return sumWorking;
    }

    public void setSumWorking(float sumWorking) {
        this.sumWorking = sumWorking;
    }

    public float getSumOver() {
        return sumOver;
    }

    public void setSumOver(float sumOver) {
        this.sumOver = sumOver;
    }

    public int getSumPunish() {
        return sumPunish;
    }

    public void setSumPunish(int sumPunish) {
        this.sumPunish = sumPunish;
    }

    public long getSumBonus() {
        return sumBonus;
    }

    public void setSumBonus(long sumBonus) {
        this.sumBonus = sumBonus;
    }

    public long getSumPunishMoney() {
        return sumPunishMoney;
    }

    public void setSumPunishMoney(long sumPunishMoney) {
        this.sumPunishMoney = sumPunishMoney;
    }

    @Override
    public String toString() {
        return "Salary{" + "id=" + id + ", empId=" + empId + ", curentDate=" + curentDate + ", salary=" + salary + '}';
    }
    
    public void create() {
        SalaryDB.create(this);
    }
    
    public static void main(String[] args) {
        for(Salary s : SalaryDB.getAllSalary()) {
            System.out.println(s.getSumWorking());
        }
    }
}
