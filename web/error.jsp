<%-- 
    Document   : error
    Created on : Oct 20, 2021, 2:03:38 AM
    Author     : ThanhNhan
--%>

<%@page contentType="text/html" isErrorPage="true" pageEncoding="UTF-8"%>
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
      <title>Error</title>
      <meta name="keywords" content="">
      <meta name="description" content="">
      <meta name="author" content="">
      <!-- site icon -->
      <link rel="icon" href="images/fevicon.png" type="image/png" />
      <!-- bootstrap css -->
      <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
      <!-- site css -->
      <link rel="stylesheet" href="assets/css/style.css" />
      <!-- responsive css -->
      <link rel="stylesheet" href="assets/css/responsive.css" />
      <!-- color css -->
      <link rel="stylesheet" href="assets/css/colors.css" />
      <!-- select bootstrap -->
      <link rel="stylesheet" href="assets/css/bootstrap-select.css" />
      <!-- scrollbar css -->
      <link rel="stylesheet" href="assets/css/perfect-scrollbar.css" />
      <!-- custom css -->
      <link rel="stylesheet" href="assets/css/custom.css" />
      <!-- calendar file css -->
      <link rel="stylesheet" href="assets/js/semantic.min.css" />
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
   </head>
   <body class="inner_page error_404">
      <div class="full_container">
         <div class="container">
            <div class="center verticle_center full_height">
               <div class="error_page">
                  <div class="center">
                     <div class="error_icon">
                         <img class="img-responsive" style="width: 100%; height: 100%" src="assets/imgs/layout_img/error.png" alt="#">
                     </div>
                  </div>
                  <br>
                  <h3 style="font-size: 30px"><%=exception.getMessage()%></h3>
                  <P></P>
                  <div class="center"><a class="main_bt" style="color: black" onclick="javascript:history.go(-1);">Quay lại</a></div>
               </div>
            </div>
         </div>
      </div>
      <!-- jQuery -->
      <script src="assets/js/jquery.min.js"></script>
      <script src="assets/js/popper.min.js"></script>
      <script src="assets/js/bootstrap.min.js"></script>
      <!-- wow animation -->
      <script src="assets/js/animate.js"></script>
      <!-- select country -->
      <script src="assets/js/bootstrap-select.js"></script>
      <!-- nice scrollbar -->
      <script src="assets/js/perfect-scrollbar.min.js"></script>
      <script>
         var ps = new PerfectScrollbar('#sidebar');
      </script>
      <!-- custom js -->
      <script src="assets/js/custom.js"></script>
   </body>
</html>
