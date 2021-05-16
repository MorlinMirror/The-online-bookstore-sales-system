package personal.moyilin.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminGradeHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Object adminGrade = request.getSession().getAttribute("adminGrade");
        System.out.println(adminGrade);
        if("1".equals(adminGrade) || "2".equals(adminGrade)){
            return true;
        }else {
            request.setAttribute("msg","请先登录高级管理员账号");
            request.getRequestDispatcher("/admin").forward(request,response);
            return false;

        }
    }
}
