package com.sumwinsun.common.interceptor;

import com.sumwinsun.common.annotation.Token;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 通过继承方式实现拦截器，拦截重复请求
 * Created by Administrator on 2016/9/21 0021.
 * 参考文档地址： http://blog.icoolxue.com/submitted-by-spring-mvc-to-prevent-data-duplication/
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //判断是否为Controller
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            //获取Controller方法中的Token注解（annotation）
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null) {
                //获取Token注解的save值，并依此判断是否需要保存--->>>跳转表单输入页面的Controller添加 @Token(save = true)
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    //此时要存储数据，故为了避免 httpServletRequest.getSession(false) 取到null值，此处选用httpServletRequest.getSession()方法
                    httpServletRequest.getSession().setAttribute("token", UUID.randomUUID().toString());
                }
                //获取Token注解的remove值，并依此判断是否需要移除--->>>提交表单的Controller添加 @Token(remove = true)
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    //判断服务端和客户端存储的Token是否相同，不相同或缺失，进行拦截
                    if (isRepeatSubmit(httpServletRequest)) {
                        return false;
                    }
                    httpServletRequest.getSession(false).removeAttribute("token");
                }
            }
            return true;
        } else {
            return super.preHandle(httpServletRequest, httpServletResponse, handler);
        }
    }
    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute("token");
        if (serverToken == null) {
            return true;
        }
        String clientToken = request.getParameter("token");
        if (clientToken == null) {
            return true;
        }
        if (!serverToken.equals(clientToken)) {
            return true;
        }
        return false;
    }
}
