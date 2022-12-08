<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Boot Application with JSP</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
    $(function() {

        let timer_id;

        function Throttling(func) {
            if(timer_id) {
                return;
            }
            timer_id = setTimeout(() => {
                func();
                timer_id = 0;
            },300)
        }
        function DelayFunction() {
            $('#ThrottleCnt').text(parseInt($('#ThrottleCnt').text()) + 1);
        }


        $('#DebouncingClick').click(() => {
            $('#ClickCnt').text(parseInt($('#ClickCnt').text()) + 1);
            Throttling(DelayFunction);
        });

    });



</script>
<body class="vsc-initialized">

<main role="main" class="container">
    <div class="jumbotron">
        <h1>쓰로틀링</h1>
        <p class="lead">실제 클릭 횟수 : <label id="ClickCnt">0</label></p>
        <p class="lead">쓰로틀링이 적용된 클릭 횟수 : <label id="ThrottleCnt">0</label></p>
        <a class="btn btn-lg btn-primary" id="DebouncingClick" rosle="button">Click!</a>
    </div>
</main>

</body>
</html>