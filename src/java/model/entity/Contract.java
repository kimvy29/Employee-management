/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.Date;

/**
 *
 * @author LinhDen
 */
public class Contract {
    private int id;
    private int empID;
    private Date fDate;
    private Date tDate;
    private float salaryBasic;
    private String note;
    private String empName;   

    public Contract(int id, int empID, Date fDate, Date tDate, float salaryBasic, String note, String empName) {
        this.id = id;
        this.empID = empID;
        this.fDate = fDate;
        this.tDate = tDate;
        this.salaryBasic = salaryBasic;
        this.note = note;
        this.empName = empName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(Date tDate) {
        this.tDate = tDate;
    }

    public float getSalaryBasic() {
        return salaryBasic;
    }

    public void setSalaryBasic(float salaryBasic) {
        this.salaryBasic = salaryBasic;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public String toString() {
        return "Contract{" + "id=" + id + ", empID=" + empID + ", fDate=" + fDate + ", tDate=" + tDate + ", salaryBasic=" + salaryBasic + ", note=" + note + ", empName=" + empName + '}';
    }
   
    
}    