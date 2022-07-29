<%-- 
    Document   : ViewAllSalary
    Created on : Jul 29, 2022, 4:04:05 PM
    Author     : ACER
--%>



<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Bảng lương</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <table class="table" style="font-size: 15px" cellspacing="3" cellpadding="3">
                    <thead>
                        <tr>
                            <th scope="col">Họ và tên</th>
                            <th scope="col">Account</th>
                            <th scope="col">Ngày chốt</th>
                            <th scope="col">Số giờ làm</th>
                            <th scope="col">Số giờ tăng ca</th>
                            <th scope="col">Số lần vi phạm</th>
                            <th scope="col">Số tiền thưởng</th>
                            <th scope="col">Số tiền phạt</th>
                            <th scope="col">Tổng lương</th>
                        </tr>
                    </thead>
                    <tbody id="data">
                        <c:forEach items="${list}" var="l">            
                            <tr>
                                <td>${l.fullName}</td>
                                <td>${l.account}</td>
                                <td>${l.curentDate}</td>
                                <td>${l.sumWorking}</td>
                                <td>${l.sumOver}</td>
                                <td style="color: red">${l.sumPunish}</td>
                                <td>${l.sumBonus}</td>
                                <td style="color: red">${l.sumPunishMoney}</td>
                                <td style="font-weight: bold">${l.salary}</td>
                            </tr>
                        </c:forEach>               
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <script>
    </script>

    <%@include file="includes/Footer.jsp" %>