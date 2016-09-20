//存放主要交互逻辑js代码-->>模块化
//调用方法 ：seckill.detail.init({})
var path = "";//定义项目部署的路径，从jsp页面调用init的时候传递
var seckill = {
	//封装秒杀相关ajax的Url
	Url : {
		now : function(){
			//获取当前服务器时间Url
			return path+"/activity/time/now";
		},
		exposer : function(activityId){
			//暴露秒杀地址Url
			return path+"/activity/"+activityId+"/exposer";
		},
		execution : function(activityId,md5){
			return path+"/activity/"+activityId+"/"+md5+"/execution";
		}
	},
	//验证手机号
	validatePhone : function(phone){
		if (phone && phone.length == 11 && !isNaN(phone)) {
			//isNaN(phone)判断是否是非数字
			return true;
		}else{
			return false;
		}
	},
	//执行秒杀函数
	handlerSeckillKill : function(activityId,node){
		//获取秒杀地址，控制实现逻辑，执行秒杀
		node.hide().html("<button class=\"btn btn-primary btn-lg\" id=\"killBtn\">开始秒杀</button>");
		$.post(seckill.Url.exposer(activityId),{},function(result){
			//回调函数中，执行交互逻辑
			if (result && result['flag']) {
				var exposer = result['data'];
				console.log(exposer)
				if (exposer['flag']) {
					//开启秒杀
					var md5 = exposer['md5Key'];
					var killUrl = seckill.Url.execution(activityId, md5);
					console.log("秒杀地址>>>>>"+killUrl);
					$("#killBtn").one("click",function(){
						//1.禁用按钮
						$(this).addClass("disabled");
						//2.执行秒杀
						$.post(killUrl,{},function(result){
							if (result && result['success']) {
								var killResult =result['data'];
								var state = killResult['state'];
								var stateInfo = killResult['stateInfo'];
								node.html("<span class=\"label label-success\" >"+stateInfo+"</span>");
							} else if(result && !result['success']){
								var killResult =result['data'];
								var state = killResult['state'];
								var stateInfo = killResult['stateInfo'];
								node.html("<span class=\"label label-success\" >"+stateInfo+"</span>");
							}
						})
					})
					node.show();
				} else {
					//未开启秒杀-->说明服务器时间和用户时间不相同，用服务器时间显示倒计时
					var now = result['now'];
					var start = result['start'];
					var end = result['end'];
					//重新进入计时
					seckill.countDown(activityId, now, start, end);
				}
			}else{
				console.log("result="+JSON.stringify(result));
			}
		});
	},
	//倒计时函数
	countDown : function(activityId, nowTime, startTime, endTime) {
		var seckillBox = $("#seckill-box");
		//时间判断
		console.log(startTime+"--"+endTime)
		if (nowTime > endTime) {
			//秒杀结束
			seckillBox.html("秒杀结束!");
		} else if(nowTime < startTime){
			//秒杀未开始，倒计时显示
			var killTime = new Date(startTime+1000);//防止前端显示时间偏移+1s
			console.log("killTime="+killTime);
			seckillBox.countdown(killTime,function(event){
				//时间格式
				var format = event.strftime("秒杀倒计时： %D天   %H时    %M分    %S秒");
				seckillBox.html(format);
			}).on("finish.countdown",function(){
				/**
				 * 时间完成后回调事件
				 * 		获取秒杀地址，控制实现逻辑，执行秒杀
				 */
				seckill.handlerSeckillKill(activityId,seckillBox);
			});
		} else{
			//秒杀开始
			seckill.handlerSeckillKill(activityId,seckillBox);
		}
	},
	// 详情页秒杀逻辑
	detail : {
		init : function(params){
			//手机验证和登陆 ，及时交互
			//规划我们的交互流程
			//zaiCookie中查找手机号
			var killPhone = $.cookie('killPhone');
			//验证手机号码
			if (!seckill.validatePhone(killPhone)) {
				//没有手机号，弹出绑定手机框
				var killPhoneModal = $("#killPhoneModal");
				killPhoneModal.modal({
					show : true,//弹出框由fade变成显示
					backdrop : 'static',//禁止位置关闭
					keyboard : false//关闭键盘事件
				})
				//弹出框提交键，绑定click事件
				$("#killPhoneBtn").bind("click",function(){
					var inputPhone = $("#killPhoneKey").val();
					console.log("inputPhone="+inputPhone);//TODO
					if(seckill.validatePhone(inputPhone)){
						//验证通过，将号码保存至Cookie(expires:7  保存7天; path:"/activity"Cookie保存路径)
						$.cookie("killPhone",inputPhone,{expires : 7 , path : "<%=path%>"});
						window.location.reload();
					}else{
						$("#killPhoneMessage").hide().html("<label class=\"label label-danger\">手机号错误</label>").show(300);
					}
				})
			}
			//已经登陆
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			var activityId = params['activityId'];
			path = params['path'];
			console.log(path);
			//获取系统时间，用于计时交互
			/*	Ajax详解：
			 * 	$.get(seckill.Url.now , {} , function(){})
			 * 				请求Url	,  传递参数	,回调函数
			 */
			$.get(seckill.Url.now() , {} , function(result){
				if (result && result["flag"]) {
					var nowTime = result['data'];
					console.log("result="+JSON.stringify(result));
					//时间判断
					seckill.countDown(activityId, nowTime, startTime, endTime);
				} else {
					console.log("result="+JSON.stringify(result));
				}
			})

		}
	}
}