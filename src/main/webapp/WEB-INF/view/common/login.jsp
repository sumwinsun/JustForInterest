<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>登陆</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<%=path%>/css/login/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>登录<sup>V 1.0</sup></h1>

<div class="login" style="margin-top:50px;">

    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
            <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a>
            <div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>


    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">

        <!--登录-->
        <div class="web_login" id="web_login">


            <div class="login-box">


                <div class="login_form">
                    <form action="<%=path%>/user/form/login" name="loginform" accept-charset="utf-8" id="login_form"
                          class="loginForm" method="post"><input type="hidden" name="did" value="0"/>
                        <input type="hidden" name="to" value="log"/>
                        <div class="uinArea" id="uinArea">
                            <label class="input-tips" for="u">帐号：</label>
                            <div class="inputOuter" id="uArea">

                                <input type="text" id="u" name="sysUserLoginName" class="inputstyle"/>
                            </div>
                        </div>
                        <div class="pwdArea" id="pwdArea">
                            <label class="input-tips" for="p">密码：</label>
                            <div class="inputOuter" id="pArea">

                                <input type="password" id="p" name="sysUserLoginPassword" class="inputstyle"/>
                            </div>
                        </div>
                        <input type="hidden" name="token" value="${token}">
                        <div style="padding-left:50px;margin-top:20px;"><input type="button" value="登 录" style="width:150px;" class="button_blue" onclick="login()"/></div>
                    </form>
                </div>

            </div>

        </div>
        <!--登录end-->
    </div>

    <!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">

        <div class="web_login">
            <form name="form2" id="regUser" accept-charset="utf-8" action="http://www.js-css.cn" method="post">
                <input type="hidden" name="to" value="reg"/>
                <input type="hidden" name="did" value="0"/>
                <ul class="reg_form" id="reg-ul">
                    <div id="userCue" class="cue">快速注册请注意格式</div>
                    <li>

                        <label for="user" class="input-tips2">用户名：</label>
                        <div class="inputOuter2">
                            <input type="text" id="user" name="user" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="passwd" class="input-tips2">密码：</label>
                        <div class="inputOuter2">
                            <input type="password" id="passwd" name="passwd" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>
                    <li>
                        <label for="passwd2" class="input-tips2">确认密码：</label>
                        <div class="inputOuter2">
                            <input type="password" id="passwd2" name="" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="qq" class="input-tips2">QQ：</label>
                        <div class="inputOuter2">

                            <input type="text" id="qq" name="qq" maxlength="10" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <div class="inputArea">
                            <input type="button" id="reg" style="margin-top:10px;margin-left:85px;" class="button_blue"
                                   value="同意协议并注册"/> <a href="#" class="zcxy" target="_blank">注册协议</a>
                        </div>

                    </li>
                    <div class="cl"></div>
                </ul>
            </form>


        </div>


    </div>
    <!--注册end-->
</div>
<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>

<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery/jquery.form.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/login/login.js"></script>
<script type="text/javascript">
function login() {
    var form = $("form[name=loginform]");
    var options  = {
        url:'<%=path%>/user/form/login',
        dataType : 'json',
        success:function(data){
            if (data && data['flag']) {
                window.location.href = "<%=path%>/index.jsp";
            } else {
                alert(data['msg']);
            }
        }
    };
    form.ajaxSubmit(options);
}
//    function login() {
//        var data = $("form[name=loginform]").submit();
//        alert(data.msg)
//        console.log(data);
//    }
</script>
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?0070105813cf9c062341342e75485819";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>

</body>
</html>