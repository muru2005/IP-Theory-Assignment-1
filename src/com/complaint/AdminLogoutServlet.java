package com.complaint;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

public class AdminLogoutServlet extends HttpServlet{
     protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
        HttpSession session=req.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        res.sendRedirect(req.getContextPath() + "/admin/login");
     }
}