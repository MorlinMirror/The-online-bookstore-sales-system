package personal.moyilin.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Object loginAdmin = request.getSession().getAttribute("loginAdmin");
         if(loginAdmin==null){
            request.setAttribute("msg","请先登录管理员账号");
            request.getRequestDispatcher("/admin").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
