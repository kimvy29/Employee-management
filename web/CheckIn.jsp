<%-- 
    Document   : CheckIn
    Created on : Jul 11, 2022, 10:41:04 PM
    Author     : ACER
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Bảng chấm công</h2>
                </div>
            </div>
            <div class="col-md-12">
                <div class="page_title">
                    <form action="check-in" method="POST">
                        <c:if test="${current == null}">
                            <input type="submit" name="action" value="Check-in" class="btn btn-success">
                        </c:if>
                        <c:if test="${current.startTime != null and current.endTime == null}">
                            <input type="submit" name="action" value="Check-out" class="btn btn-danger">
                        </c:if>
                        <c:if test="${current.startTime != null and current.endTime != null and current.startOverTime == null}">
                            <input type="submit" name="action" value="Check-in-overtime" class="btn btn-success">
                        </c:if>
                            <c:if test="${current.startTime != null and current.endTime != null and current.startOverTime != null and current.endOverTime == null}">
                            <input type="submit" name="action" value="Check-out-overtime" class="btn btn-danger">
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <table class="table" style="font-size: 15px" cellspacing="3" cellpadding="3">
                    <thead>
                        <tr>
                            <th scope="col">Ngày</th>
                            <th scope="col">Start time</th>
                            <th scope="col">End time</th>
                            <th scope="col">Số lần vi phạm</th>
                            <th scope="col">Start over time</th>
                            <th scope="col">End over time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="l">            
                            <tr>
                                <td>${l.currentDate}</td>
                                <td>${l.startTime}</td>
                                <td>${l.endTime}</td>
                                <td>${l.punish}</td>
                                <td>${l.startOverTime}</td>
                                <td>${l.endOverTime}</td>
                            </tr>
                        </c:forEach>               
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <%@include file="includes/Footer.jsp" %>