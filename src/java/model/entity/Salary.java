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

    public Salary() {
    }

    public Salary(int id, int empId, Date curentDate, long salary) {
        this.id = id;
        this.empId = empId;
        this.curentDate = curentDate;
        this.salary = salary;
    }
    
    public Salary(int empId, long salary) {
        this.empId = empId;
        this.salary = salary;
    }
    
    public Salary(int id) {
        this(SalaryDB.getSalary(id));
    }
    
    public Salary(Salary s) {
        this(s.id, s.empId, s.curentDate, s.salary);
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

    @Override
    public String toString() {
        return "Salary{" + "id=" + id + ", empId=" + empId + ", curentDate=" + curentDate + ", salary=" + salary + '}';
    }
    
    public void create() {
        SalaryDB.create(this);
    }
}
