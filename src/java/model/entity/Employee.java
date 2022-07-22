/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import model.dao.Characters;
import model.dao.EmployeeDB;
import model.dao.TimeKeepingDB;

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
    private String avatar;
    private boolean sex;

    public Employee(String fullName, String email, String address, String tel, int positionId, int managerId, boolean activity, int departmentId, String avatar, boolean sex) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.tel = tel;
        this.positionId = positionId;
        this.managerId = managerId;
        this.activity = activity;
        this.departmentId = departmentId;
        this.avatar = avatar;
        this.sex = sex;
    }
    
    public Employee(String fullName, String email, String address, String tel, int positionId, int managerId, int departmentId, boolean sex) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.tel = tel;
        this.positionId = positionId;
        this.managerId = managerId;
        this.departmentId = departmentId;
        this.sex = sex;
    }

    public Employee(int id, String fullName, String email, String address, String tel, int positionId, int managerId, boolean activity, int departmentId, boolean sex) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.tel = tel;
        this.positionId = positionId;
        this.managerId = managerId;
        this.activity = activity;
        this.departmentId = departmentId;
        this.avatar = avatar;
        this.sex = sex;
    }

    public Employee(int id, String fullName, String email, String address, String tel, int positionId, String positionName, int managerId, boolean activity, int departmentId, String avatar, boolean sex) {
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
        this.avatar = avatar;
        this.sex = sex;
    }
    
    public Employee(Employee e){
        this(e.id, e.fullName, e.email, e.address, e.tel, e.positionId, e.positionName, e.managerId, e.activity, e.departmentId, e.avatar, e.sex);
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
        return Characters.conver(fullName);
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
        return Characters.conver(address);
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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        if(avatar == null) {
            if(this.isSex()) {
                return "./assets/imgs/avatar/nam.jpg";
            } else {
                return "./assets/imgs/avatar/nu.jpg";
            }
        } else {
            return avatar;
        }
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getDepartmentName(){
        return new Department(this.departmentId).getName();
    }
    
    public int getDepartmentRoomNo(){
        return new Department(this.departmentId).getRoomNo();
    }
    
    public long getSalaryBasic() {
        return new Contract(this).getSalaryBasic()/20/8;
    }
    
    public String getGen() {
        if(this.isSex()) {
            return "Nam";
        } else {
            return "Nữ";
        }
    }
    
    public String getBlock() {
        if(this.activity) {
            return "Khóa";
        } else {
            return "Mở";
        }
    }

//    @Override
//    public String toString() {
//        return "Employee{" + "id=" + id + ", fullName=" + fullName + ", email=" + email + ", address=" + address + ", tel=" + tel + ", positionId=" + positionId + ", positionName=" + positionName + ", managerId=" + managerId + ", activity=" + activity + ", departmentId=" + departmentId + ", avatar=" + avatar + ", sex=" + sex + '}';
//    }
    
    public Employee create(Contract c){
        return EmployeeDB.create(this, c);
    }
    
    public void update() {
        EmployeeDB.update(this);
    }
    
    public void delete() {
        EmployeeDB.delete(this);
    }
    
    public void startTime() {
        TimeKeeping.startTime(this);
    }
    
    public void paySalary() {
        new Salary(this.id, (long) (this.getSalaryBasic()*TimeKeepingDB.rateSalary(this)[0] - 20000*TimeKeepingDB.rateSalary(this)[1] + 1.5*this.getSalaryBasic()*TimeKeepingDB.rateSalary(this)[2])).create();
    }
    
    public void block() {
        this.setActivity(!this.isActivity());
        EmployeeDB.block(this);
    }
}
