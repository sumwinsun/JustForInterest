<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../../common/tag.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="screen-orientation" content="">
    <title>打地鼠</title>
    <link rel="stylesheet" href="<%=path%>/css/activity/pickBag.css">
</head>
<body>
    <div class="con">
        <span class="score_con">抓住了<span id="score">0</span>只"鸡"祥</span>
        <span id="time_left">15s</span>
        <img class="bg" src="<%=path%>/images/activity/bg.jpg">
        <canvas id="canvas" ></canvas>
    </div>

    <script src="<%=path%>/js/jquery/jquery-1.9.0.min.js"></script>
    <script src="<%=path%>/js/activity/pickBag.js"></script>
</body>
<script>
    initData('<%=path%>/images/activity/xx.jpg', '<%=path%>/images/activity/bg.jpg');
</script>
</html>