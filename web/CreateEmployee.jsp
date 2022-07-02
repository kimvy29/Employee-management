<%-- 
    Document   : CreateEmployee
    Created on : Jul 3, 2022, 3:22:13 AM
    Author     : ACER
--%>

<%@include file="./includes/Header.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Thêm mới nhân viên</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <form action="create-employee" method="POST" style="display: flex">
                    <div class="col-6">
                        <h4>Thông tin nhân viên</h4>
                        <div class="form-group">
                            <label for="fullName" class="text-info">Tên nhân viên:</label><br>
                            <input type="text" name="fullName" id="fullName" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="email" class="text-info">Email:</label><br>
                            <input type="email" name="email" id="email" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="address" class="text-info">Địa chỉ:</label><br>
                            <input type="text" name="address" id="address" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="tel" class="text-info">Số điện thoại:</label><br>
                            <input type="text" name="tel" id="tel" class="form-control" autocomplete="off" required maxlength="10" pattern="[0-9]{1,10}" title="Số điện thoại chỉ chứa các ký tự số từ 0-9">
                        </div>
                        <div class="form-group">
                            <label for="positionId" class="text-info">Chức vụ:</label><br>
                            <select class="form-control" name="positionId" id="positionId">
                                <option value="2">Trưởng phòng</option>
                                <option value="3">Quản lý</option>
                                <option value="4" selected>Nhân viên</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="managerId" class="text-info">Người quản lý:</label><br>
                            <select class="form-control" name="managerId" id="managerId">
                                <option value="0">--------------------</option>
                                <c:forEach items="${manager}" var="m">
                                    <option value="${m.id}">${m.fullName}</option>
                                </c:forEach>      
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="departmentId" class="text-info">Phòng ban:</label><br>
                            <select class="form-control" name="departmentId" id="departmentId">
                                <c:forEach items="${department}" var="d">
                                    <option value="${d.id}">${d.name}</option>
                                </c:forEach>      
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="sex" class="text-info">Giới tính:</label><br>
                            <select class="form-control" name="sex" id="sex">
                                <option value="true" selected>Nam</option>
                                <option value="false">Nữ</option>     
                            </select>
                        </div>
                    </div>
                    <div class='col-6'>
                        <h4>Thông tin hợp đồng</h4>
                        <div class="form-group">
                            <label for="basicSalary" class="text-info">Lương cơ bản:</label><br>
                            <input type="number" name="basicSalary" id="basicSalary" class="form-control" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="tDate" class="text-info">Hạn hợp đồng:</label><br>
                            <input type="date" name="tDate" required>
                        </div>
                        <div class="form-group">
                            <label for="note" class="text-info">Ghi chú:</label><br>
                            <textarea name="note" id="note" class="form-control" style="overflow: auto" rows="10"></textarea>
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

    <%@include file="./includes/Footer.jsp" %>
