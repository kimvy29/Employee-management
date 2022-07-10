<%-- 
    Document   : UpdateDepartment
    Created on : Jul 10, 2022, 12:13:16 AM
    Author     : ACER
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Thông tin phòng ${department.name}</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <form action="update-department" method="POST">

                    <table class="table" style="font-size: 15px; color: black; font-family: auto" cellspacing="3" cellpadding="3">
                        <tr>
                            <th scope="col">Tên phòng</th>
                            <td><input style="border: none; background: none" type="text" name="name" value="${department.name}"></td>
                        </tr>
                        <tr>
                            <th scope="col">Số phòng</th>
                            <td><input style="border: none; background: none" type="number" name="roomNo" value="${department.roomNo}"></td>
                        </tr>
                        <tr>
                            <th scope="col">Trưởng phòng</th>
                            <td>
                                <select style="border: none; background: none" class="form-control" name="managerId" id="managerId">
                                    <option value="0" >-----------------------------</option>
                                    <c:forEach items="${manager}" var="m">
                                        <option value="${m.id}" <c:if test="${m.id == department.managerId}">selected</c:if>>${m.fullName}</option>
                                    </c:forEach>      
                                </select>
                            </td>
                        </tr>
                        </table>
                        <input type="number" name="id" value="${department.id}" hidden>
                    <div style="display: flex">
                        <div class="center" style="margin: 0 10px"><a class="main_bt" onclick="javascript:history.go(-1);">Quay lại</a></div>
                        <div class="center"><button type="submit" class="main_bt" style="background: #0069d9; color: black">Sửa</button></div>
                    </div>

                </form>
            </div>

        </div>
    </div>


    <%@include file="includes/Footer.jsp" %>