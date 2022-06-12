/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

/**
 *
 * @author ACER
 */
public class Department {
    private int id;
    private String name;
    private int roomNo;
    private int managerId;

    public Department() {
    }

    public Department(String name, int roomNo, int managerId) {
        this.name = name;
        this.roomNo = roomNo;
        this.managerId = managerId;
    }

    public Department(int id, String name, int roomNo, int managerId) {
        this.id = id;
        this.name = name;
        this.roomNo = roomNo;
        this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", roomNo=" + roomNo + ", managerId=" + managerId + '}';
    }
}
