<%-- 
    Document   : ContractDetail
    Created on : Jul 4, 2022, 10:46:38 PM
    Author     : ACER
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Hợp đồng nhân viên: ${contract.fullName}</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <form action="contract-detail" method="POST">

                    <table class="table" style="font-size: 15px; color: black; font-family: auto" cellspacing="3" cellpadding="3">
                        <tr>
                            <th scope="col">Họ và tên</th>
                            <td>${contract.fullName}</td>
                        </tr>
                        <tr>
                            <th scope="col">Account</th>
                            <td>${contract.userName}</td>
                        </tr>
                        <tr>
                            <th scope="col">Ngày bắt đầu</th>
                            <td>${contract.frDate}</td>
                        </tr>
                        <tr>
                            <th scope="col">Ngày kết thúc</th>
                            <td id="toDate">${contract.toDate}</td>
                        </tr>
                        <tr>
                            <th scope="col">Lương cơ bản</th>
                            <td id="salary">${contract.salaryBasic}</td>
                        </tr>
                        <tr>
                            <th scope="col">Ghi chú</th>
                            <td id="note">${contract.note} </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="center" style="margin: 0 10px"><a class="main_bt" onclick="javascript:history.go(-1);">Quay lại</a></div>
            <c:if test="${acc.roleId == 1}">
                <div class="center"><a class="main_bt" style="background: #0069d9; color: black" id="edit" onclick="edit('${contract.id}', '${contract.toDate}', '${contract.salaryBasic}', '${contract.note}','${contract.empId}')">Sửa</a></div>
            </c:if>
            <div class="center" id="submit"></div>
        </div>
    </div>

    <script type="text/javascript">
        function edit(id, toDate, salaryBasic, note, empId) {
            var edit = document.getElementById("edit").innerHTML;
            if (edit === 'Sửa') {
                document.getElementById("toDate").innerHTML = '<input style="border: none; background: none" id="valueToDate" type="date" value="' + toDate + '">';
                document.getElementById("salary").innerHTML = '<input style="border: none; background: none" id="valueSalary" type="number" value="' + salaryBasic + '">';
                document.getElementById("note").innerHTML = '<textarea id="valueNote" style="border: none; background: none">' + note + '</textarea>';
                document.getElementById("edit").innerHTML = "Đồng ý";
            } else {
                $.ajax({
                    url: '/employee-management/contract-detail',
                    type: 'POST',
                    data: {
                        id: id,
                        toDate: document.getElementById("valueToDate").value,
                        salaryBasic: document.getElementById("valueSalary").value,
                        note: document.getElementById("valueNote").value,
                    },
                    success: function () {
                        console.log("contract-detail?id="+empId);
                        location.reload();
                    }
                });
            }
        }
    </script>

    <%@include file="includes/Footer.jsp" %>