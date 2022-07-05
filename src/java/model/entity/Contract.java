/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.text.SimpleDateFormat;
import java.sql.Date;
import model.dao.ContractDB;

/**
 *
 * @author LinhDen
 */
public class Contract {
    private int id;
    private int empId;
    private Date fDate;
    private Date tDate;
    private long salaryBasic;
    private String note;  

    public Contract(int id, int empId, Date fDate, Date tDate, long salaryBasic, String note) {
        this.id = id;
        this.empId = empId;
        this.fDate = fDate;
        this.tDate = tDate;
        this.salaryBasic = salaryBasic;
        this.note = note;
    }
    
    public Contract(int id, Date tDate, long salaryBasic, String note) {
        this.id = id;
        this.tDate = tDate;
        this.salaryBasic = salaryBasic;
        this.note = note;
    }
    
    public Contract(Date tDate, long salaryBasic, String note) {
        this.tDate = tDate;
        this.salaryBasic = salaryBasic;
        this.note = note;
    }
    
    public Contract(Contract c){
        this(c.id, c.empId, c.fDate, c.tDate, c.salaryBasic, c.note);
    }
    
    public Contract(int id) {
        this(ContractDB.GetContractById(id));
    }
    
    public Contract(Employee e){
        this(ContractDB.GetContractByEmployeeId(e.getId()));
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

    public void setEmpID(int empId) {
        this.empId = empId;
    }
    
    public String getFullName() {
        return new Employee(this.empId).getFullName();
    }

    public String getFrDate() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(fDate);
    }

    public void setFDate(Date fDate) {
        this.fDate = fDate;
    }

    public Date getFDate() {
        return fDate;
    }


    public Date getTDate() {
        return tDate;
    }

    public void setTDate(Date tDate) {
        this.tDate = tDate;
    }    
    

    public String getToDate() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(tDate);
    }

    public long getSalaryBasic() {
        return salaryBasic;
    }

    public void setSalaryBasic(long salaryBasic) {
        this.salaryBasic = salaryBasic;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Contract{" + "id=" + id + ", empId=" + empId + ", fDate=" + fDate + ", tDate=" + tDate + ", salaryBasic=" + salaryBasic + ", note=" + note + '}';
    }
    
    public void create(){
        ContractDB.create(this);
    }
    
    public void update() {
        ContractDB.update(this);
    }
    
    public String getUserName() {
        return new Account(empId).getUserName();
    }
}    