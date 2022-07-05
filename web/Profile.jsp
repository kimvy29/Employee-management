<%-- 
    Document   : Profile
    Created on : Jul 5, 2022, 9:41:58 PM
    Author     : ACER
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Thông tin nhân viên: ${employee.fullName}</h2>
                </div>
                <div style="display: flex; align-items: flex-end">
                    <img src="${employee.avatar}" style="max-height: 300px; margin: 2%" alt="${employee.fullName}">
                    <form action="profile" method="POST" enctype="multipart/form-data" style="margin: 2%">
                        <input type="file" name="avatar" required style="margin: 2%"><br>
                        <input class="btn btn-compose" style="width: unset; margin: 2%" type="submit" value="Đổi avatar">
                    </form>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <form action="contract-detail" method="POST">

                    <table class="table" style="font-size: 15px; color: black; font-family: auto" cellspacing="3" cellpadding="3">
                        <tr>
                            <th scope="col">Họ và tên</th>
                            <td>${employee.fullName}</td>
                        </tr>
                        <tr>
                            <th scope="col">Account</th>
                            <td>${employee.userName}</td>
                        </tr>
                        <tr>
                            <th scope="col">Email</th>
                            <td>${employee.email}</td>
                        </tr>
                        <tr>
                            <th scope="col">Địa chỉ</th>
                            <td>${employee.address}</td>
                        </tr>
                        <tr>
                            <th scope="col">Số điện thoại</th>
                            <td>${employee.tel}</td>
                        </tr>
                        <tr>
                            <th scope="col">Chức vụ</th>
                            <td>${employee.positionName}</td>
                        </tr>
                        <tr>
                            <th scope="col">Quản lý</th>
                            <td>${employee.managerName}</td>
                        </tr>
                        <tr>
                            <th scope="col">Phòng làm việc</th>
                            <td>Phòng ${employee.departmentRoomNo} - ${employee.departmentName} </td>
                        </tr>
                        <tr>
                            <th scope="col">Giới tính</th>
                            <td>${employee.gen}</td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="center" style="margin: 0 10px"><a class="main_bt" onclick="javascript:history.go(-1);">Quay lại</a></div>
            <div class="center" style="margin: 0 10px"><a href="change-password" class="main_bt btn-danger" style="background: #007bff">Đổi mật khẩu</a></div>
        </div>
    </div>

    <%@include file="includes/Footer.jsp" %>
