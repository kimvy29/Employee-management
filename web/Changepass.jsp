<%-- 
    Document   : Changepass
    Created on : Jul 3, 2022, 7:54:30 PM
    Author     : ACER
--%>


<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Đổi mật khẩu</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <form action="change-password" method="POST">
                    <div class='col-6'>
                        <div class="form-group">
                            <label for="oldPass" class="text-info">Mật khẩu cũ:</label><br>
                            <input type="password" name="oldPass" id="oldPass" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="newPass" class="text-info">Mật khẩu mới:</label><br>
                            <input type="password" name="newPass" id="newPass" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <br>
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="Đồng ý">
                        </div>
                    </div>
                </form>
            </div>


        </div>

        <!-- graph -->

        <!-- end graph -->


    </div>
    <!-- footer -->


<%@include file="includes/Footer.jsp" %>