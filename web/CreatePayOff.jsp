<%-- 
    Document   : CreatePayOff
    Created on : Jul 25, 2022, 11:51:36 PM
    Author     : ACER
--%>

<%@include file="./includes/Header.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Thêm mới thưởng phạt cho nhân viên</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-6">
                <form action="create-payoff" method="POST" onsubmit="disable()">
                    <div class="form-group">
                        <label for="employeeId" class="text-info">Tên nhân viên:</label><br>
                        <select class="form-control" name="employeeId" id="employeeId">
                            <c:forEach var="e" items="${employee}">
                                <option value='${e.id}'>${e.fullName}</option>     
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="payOff" class="text-info">Thưởng/Phạt:</label><br>
                        <select class="form-control" name="payOff" id="payOff">
                            <option value='true'>Thưởng</option>
                            <option value='false'>Phạt</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="note" class="text-info">Lý do:</label><br>
                        <input type="text" name="note" id="note" class="form-control" autocomplete="off" required>
                    </div>
                    <div class="form-group">
                        <label for="money" class="text-info">Số tiền:</label><br>
                        <input type="number" name="money" id="money" class="form-control" autocomplete="off" required>
                    </div>
                    <input type="submit" name="submit" id="submit" class="btn btn-info btn-md" value="Đồng ý">
                </form>
            </div>

        </div>

    </div>

    <%@include file="./includes/Footer.jsp" %>
