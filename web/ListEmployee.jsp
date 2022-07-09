<%-- 
    Document   : ListEmployee
    Created on : Jul 4, 2022, 8:32:13 PM
    Author     : ACER
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Danh sách nhân viên</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <table class="table" style="font-size: 15px" cellspacing="3" cellpadding="3">
                    <thead>
                        <tr>
                            <th scope="col">Tên nhân viên</th>
                            <th scope="col">Account</th>
                            <th scope="col">Avatar</th>
                            <th scope="col">Người quản lý</th>
                            <th scope="col">Giới tính</th>
                            <th scope="col">Phòng ban</th>
                            <th scope="col">Chức vụ</th>
                                <c:if test="${acc.roleId == 1}">
                                <th scope="col">Khóa tài khoản</th>
                                <th scope="col">Update</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="l">            
                            <tr>
                                <td><a href="contract-detail?id=${l.id}">${l.fullName}</a></td>
                                <td>${l.userName}</td>
                                <td><img width="100px" src="${l.avatar}" alt="${l.fullName}"></td>
                                <td>${l.managerName}</td>
                                <td>${l.gen}</td>
                                <td>${l.departmentName}</td>
                                <td>${l.positionName}</td>
                                <c:if test="${acc.roleId == 1}">
                                    <td><a id="block${l.id}" onclick="block(${l.id})" style="cursor: pointer">${l.block}</a></td>
                                    <td><a href="update-employee?id=${l.id}">Update</a></td>
                                    </c:if>
                            </tr>
                        </c:forEach>               
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script>
        function block(id) {
            $.ajax({
                url: '/employee-management/list-employee',
                type: 'POST',
                data: {
                    id: id
                },
                success: function () {
                    var status = document.getElementById("block" + id).innerHTML;
                    if (status.trim() == "Khóa") {
                        document.getElementById("block" + id).innerHTML = "Mở";
                    } else {
                        document.getElementById("block" + id).innerHTML = "Khóa";
                    }
                }
            });
        }
    </script>

    <%@include file="includes/Footer.jsp" %>

