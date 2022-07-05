<%-- 
    Document   : Header_admin
    Created on : Oct 23, 2021, 10:53:36 PM
    Author     : ThanhNhan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.entity.Account"%>
<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<jsp:useBean id="acc" class="model.entity.Account" scope="session"></jsp:useBean>
<c:if test="${acc.userName == null}">
    <jsp:forward page="login"></jsp:forward>
</c:if>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Quản lý nhân viên</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- site icon -->
        <link rel="icon" href="./assets/imgs/fevicon.png" type="image/png" />
        <!-- bootstrap css -->
        <link rel="stylesheet" href="./assets/css/bootstrap.min.css" />
        <!-- site css -->
        <link rel="stylesheet" href="./assets/css/style.css" />
        <!-- responsive css -->
        <link rel="stylesheet" href="./assets/css/responsive.css" />
        <!-- color css -->
        <link rel="stylesheet" href="./assets/css/colors.css" />
        <!-- select bootstrap -->
        <link rel="stylesheet" href="./assets/css/bootstrap-select.css" />
        <!-- scrollbar css -->
        <link rel="stylesheet" href="./assets/css/perfect-scrollbar.css" />
        <!-- custom css -->
        <link rel="stylesheet" href="./assets/css/custom.css" />
        <!-- calendar file css -->
        <link rel="stylesheet" href="./assets/js/semantic.min.css" />
        <!-- fancy box js -->
        <link rel="stylesheet" href="./assets/css/jquery.fancybox.css" />

        <link rel="stylesheet" href="./assets/css/font-awesome.min.css" />
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="inner_page email_page">
        <div class="full_container">
            <div class="inner_container">
                <!-- Sidebar  -->
                <nav id="sidebar">
                    <div class="sidebar_blog_1">
                        <div class="sidebar-header">
                            <div class="logo_section">
                                <a href="index.html"><img class="logo_icon img-responsive" src="./assets/imgs/logo/logo_icon.png" alt="#" /></a>
                            </div>
                        </div>
                        <div class="sidebar_user_info">
                            <div class="icon_setting"></div>
                            <div class="user_profle_side">
                                <div class="user_img"><img class="img-responsive" src='${acc.avatar}' alt="#" /></div>
                                <div class="user_info">
                                    <h6>${acc.fullName}</h6>
                                    <p><span class="online_animation"></span> Online</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar_blog_2">
                        <h4>General</h4>
                        <ul class="list-unstyled components">
                            <li class="active">
                                <a href="#dashboard" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-dashboard yellow_color"></i> <span>Dashboard</span></a>
                                <ul class="collapse list-unstyled" id="dashboard">
                                    <li>
                                        <a href="dashboard.html">> <span>Default Dashboard</span></a>
                                    </li>
                                    <li>
                                        <a href="dashboard_2.html">> <span>Dashboard style 2</span></a>
                                    </li>
                                </ul>
                            </li>
                            <c:if test="${acc.roleId == 1}">
                                <li><a href="create-employee"><i class="fa fa-user-plus green_color"></i> <span>Thêm nhân viên</span></a></li>
                                </c:if>
                                <c:if test="${acc.roleId == 1 or acc.roleId == 2}">
                                <li><a href="list-employee"><i class="fa fa-users green_color"></i> <span>Danh sách nhân viên</span></a></li>
                                </c:if>
                            <li>
                                <a href="#element" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-diamond purple_color"></i> <span>Elements</span></a>
                                <ul class="collapse list-unstyled" id="element">
                                    <li><a href="general_elements.html">> <span>General Elements</span></a></li>
                                    <li><a href="media_gallery.html">> <span>Media Gallery</span></a></li>
                                    <li><a href="icons.html">> <span>Icons</span></a></li>
                                    <li><a href="invoice.html">> <span>Invoice</span></a></li>
                                </ul>
                            </li>
                            <c:if test="${acc.roleId != 1}">
                                <li><a href="contract-detail?id=${acc.empId}"><i class="fa fa-file purple_color2"></i> <span>Xem hợp đồng</span></a></li>
                                </c:if>
                            <li>
                                <a href="#apps" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-object-group blue2_color"></i> <span>Apps</span></a>
                                <ul class="collapse list-unstyled" id="apps">
                                    <li><a href="email.html">> <span>Email</span></a></li>
                                    <li><a href="calendar.html">> <span>Calendar</span></a></li>
                                    <li><a href="media_gallery.html">> <span>Media Gallery</span></a></li>
                                </ul>
                            </li>
                            <li><a href="price.html"><i class="fa fa-briefcase blue1_color"></i> <span>Pricing Tables</span></a></li>
                            <li>
                                <a href="contact.html">
                                    <i class="fa fa-paper-plane red_color"></i> <span>Contact</span></a>
                            </li>
                            <li class="active">
                                <a href="#additional_page" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-clone yellow_color"></i> <span>Additional Pages</span></a>
                                <ul class="collapse list-unstyled" id="additional_page">
                                    <li>
                                        <a href="profile.html">> <span>Profile</span></a>
                                    </li>
                                    <li>
                                        <a href="project.html">> <span>Projects</span></a>
                                    </li>
                                    <li>
                                        <a href="login.html">> <span>Login</span></a>
                                    </li>
                                    <li>
                                        <a href="404_error.html">> <span>404 Error</span></a>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="map.html"><i class="fa fa-map purple_color2"></i> <span>Map</span></a></li>
                            <li><a href="charts.html"><i class="fa fa-bar-chart-o green_color"></i> <span>Charts</span></a></li>
                            <li><a href="settings.html"><i class="fa fa-cog yellow_color"></i> <span>Settings</span></a></li>
                        </ul>
                    </div>
                </nav>
                <!-- end sidebar -->
                <!-- right content -->
                <div id="content">
                    <!-- topbar -->
                    <div class="topbar">
                        <nav class="navbar navbar-expand-lg navbar-light">
                            <div class="full">
                                <button type="button" id="sidebarCollapse" class="sidebar_toggle"><i class="fa fa-bars"></i></button>
                                <div class="logo_section">
                                    <a href="index.html"><img class="img-responsive" src="./assets/imgs/logo/logo.png" alt="#" /></a>
                                </div>
                                <div class="right_topbar">
                                    <div class="icon_info">
                                        <ul>
                                            <li><a href="#"><i class="fa fa-bell-o"></i><span class="badge">2</span></a></li>
                                            <li><a href="#"><i class="fa fa-question-circle"></i></a></li>
                                            <li><a href="#"><i class="fa fa-envelope-o"></i><span class="badge">3</span></a></li>
                                        </ul>
                                        <ul class="user_profile_dd">
                                            <li>
                                                <a class="dropdown-toggle" data-toggle="dropdown"><img class="img-responsive rounded-circle" src="${acc.avatar}" alt="#" /><span class="name_user">${acc.fullName}</span></a>
                                                <div class="dropdown-menu">
                                                    <c:if test="${acc.roleId != 1}">
                                                        <a class="dropdown-item" href="profile">My Profile</a>
                                                    </c:if>
                                                    <c:if test="${acc.roleId == 1}">
                                                        <a class="dropdown-item" href="change-password">Đổi mật khẩu</a>
                                                    </c:if>
                                                    <a class="dropdown-item" href='logout'><span>Log Out</span> <i class="fa fa-sign-out"></i></a>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </div>
                    <!-- end topbar -->
                    <!-- dashboard inner -->
                    ..
                    <!-- end dashboard inner -->

                    <!-- jQuery -->
