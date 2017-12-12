package cn.medemede.excelcard.controller;

import cn.medemede.excelcard.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        HttpSession session=request.getSession();
        String path=request.getRequestURI();//获得用户请求的URL；
        User user= (User) session.getAttribute("user");
        /*String userid= (String) request.getAttribute("userId");
        String pwd= (String) request.getAttribute("pwd");
        Integer power= (Integer) request.getAttribute("power");
        User user=new User();
        user.setId(userid);
        user.setPwd(pwd);
        user.setPower(power);*/
        if(path.indexOf("/index.jsp")>-1){
            chain.doFilter(req, resp);
        }else if(user==null){
            response.sendRedirect("index.jsp");
        }else{
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
