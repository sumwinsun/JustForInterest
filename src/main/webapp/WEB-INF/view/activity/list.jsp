<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@ include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>秒杀列表页</title>
	<%@include file="../common/head.jsp" %>
</head>
<body>
	<div class="container">
		<!-- 头 -->
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>秒杀列表</h2>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>名称</th>
							<th>库存</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>创建时间</th>
							<th>详情页</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sk" items="${data.data}">
							<tr>
								<td>${sk.activityName}</td>
								<td>${sk.activityPrize.prizeNum}</td>
								<td>
									<fmt:formatDate value="${sk.activityStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<fmt:formatDate value="${sk.activityEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<fmt:formatDate value="${sk.activityCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<a class="btn btn-info" href="<%=path%>/activity/${sk.activityId}/detail">link</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function () {
		$.ajax({
			url:"<%=path%>/user/hello",
			success:function () {
				console.log("success")
					setTimeout(function () {
						window.location.href = "<%=path%>/index.jsp";
					},1000)
			}
		})
	})
</script>
</html>