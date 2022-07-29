<%-- 
    Document   : ViewPayOff
    Created on : Jul 29, 2022, 3:42:02 PM
    Author     : ACER
--%>



<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Bảng thưởng/phạt</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <table class="table" style="font-size: 15px" cellspacing="3" cellpadding="3">
                    <thead>
                        <tr>
                            <th scope="col">Ngày</th>
                            <th scope="col">Thưởng/Phạt</th>
                            <th scope="col">Lý do</th>
                            <th scope="col">Số tiền</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="l">            
                            <tr>
                                <td>${l.currentDate}</td>
                                <td>${l.status}</td>
                                <td>${l.note}</td>
                                <td>${l.money}</td>
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