/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import model.dao.EmployeeDB;

/**
 *
 * @author NguyenVy
 */
public class Employee {
    private int id;
    private String fullName;
    private String email;
    private String address;
    private String tel;
    private int positionId;
    private String positionName;
    private int managerId;
    private boolean activity;
    private int departmentId;

    public Employee(String fullName, String email, String address, String tel, int positionId, int managerId, boolean activity, int departmentId) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.tel = tel;
        this.positionId = positionId;
        this.managerId = managerId;
        this.activity = activity;
        this.departmentId = departmentId;
    }
    
    public Employee(String fullName, String email, String address, String tel, int positionId, int managerId, int departmentId) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.tel = tel;
        this.positionId = positionId;
        this.managerId = managerId;
        this.departmentId = departmentId;
    }

    public Employee(int id, String fullName, String email, String address, String tel, int positionId, int managerId, boolean activity, int departmentId) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.tel = tel;
        this.positionId = positionId;
        this.managerId = managerId;
        this.activity = activity;
        this.departmentId = departmentId;
    }

    public Employee(int id, String fullName, String email, String address, String tel, int positionId, String positionName, int managerId, boolean activity, int departmentId) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.tel = tel;
        this.positionId = positionId;
        this.positionName = positionName;
        this.managerId = managerId;
        this.activity = activity;
        this.departmentId = departmentId;
    }
    
    public Employee(Employee e){
        this(e.id, e.fullName, e.email, e.address, e.tel, e.positionId, e.managerId, e.activity, e.departmentId);
    }
    
    public Employee(int id) {
        this(EmployeeDB.getEmployee(id));
    }

    public Employee() {
    }

    public String getUserName() {
        return new Account(this.id).getUserName();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }
    
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    
    public String getManagerName() {
        try {
            return new Employee(this.managerId).getFullName();
        } catch (Exception e) {
            return "-";
        }
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getDepartmentName(){
        return new Department(this.departmentId).getName();
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fullName=" + fullName + ", email=" + email + ", address=" + address + ", tel=" + tel + ", positionId=" + positionId + ", managerId=" + managerId + ", activity=" + activity + ", departmentId=" + departmentId + '}';
    }
    
    public void create(Contract c){
        EmployeeDB.create(this, c);
    }
    
    public void update() {
        EmployeeDB.update(this);
    }
    
    public void delete() {
        EmployeeDB.delete(this);
    }
    
}
