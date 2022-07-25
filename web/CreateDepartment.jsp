<%-- 
    Document   : CreateDepartment
    Created on : Jul 9, 2022, 11:54:46 PM
    Author     : ACER
--%>

<%@include file="./includes/Header.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Thêm mới phòng ban</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-6">
                <form action="create-department" method="POST" onsubmit="disable()">
                    <h4>Thông tin phòng</h4>
                    <div class="form-group">
                        <label for="name" class="text-info">Tên phòng:</label><br>
                        <input type="text" name="name" id="name" class="form-control" autocomplete="off" required>
                    </div>
                    <div class="form-group">
                        <label for="roomNo" class="text-info">Số phòng:</label><br>
                        <input type="number" name="roomNo" id="roomNo" class="form-control" autocomplete="off" required>
                    </div>
                    <input type="submit" name="submit" id="submit" class="btn btn-info btn-md" value="Đồng ý">
                </form>
            </div>

        </div>

    </div>

    <%@include file="./includes/Footer.jsp" %>
