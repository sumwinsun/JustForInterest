<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@ include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>秒杀详情页</title>
	<%@include file="../common/head.jsp" %>
</head>
<body>
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="panner-heading">
				<h1>${data.data.activityName}</h1>
			</div>
			<div class="panel-body">
				<h2 class="text-danger">
					<!-- 显示Time图标 -->
					<span class="glyphicon glyphicon-time"></span>
					<!-- 显示倒计时时间 -->
					<span class="glyphicon" id="seckill-box"></span>
				</h2>
			</div>
		</div>
	</div>
	<!-- 弹出登陆层，输入号码执行秒杀 -->
	<div id="killPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
			
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>秒杀电话：
					</h3>
				</div>
				
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhoneKey"
								placeholder="请填写手机号" class="form-control"/>
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<!-- 验证信息 -->
					<span id="killPhoneMessage" class="glyphicon"></span>
					<button type="button" id="killPhoneBtn" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span>
						Submit
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<!-- 使用CDN获取公共js或css服务  http://www.bootcdn.cn/ -->
<!-- jQuery Cookie操作插件 -->
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- jQuery 倒计时插件 -->
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>

<!-- 引入本地js资源 -->
<script src="<%=path%>/js/activity/seckill.js" type="text/javascript"></script>
<!-- 调用本地js资源，完成交互 -->
<script type="text/javascript">
	$(function(){
		//使用EL表达式传入参数
		seckill.detail.init({
			activityId : ${data.data.activityId},
			startTime : ${data.data.activityStartTime.time},//获取开始时间的毫秒值
			endTime : ${data.data.activityEndTime.time},
			path : "<%=path%>"
		});
	})
</script>
</html>