package com.complaint;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class AdminLoginServlet extends HttpServlet{
 public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{   
   String uname=req.getParameter("username");
   String pwd=req.getParameter("password");
  
   try{
     Connection con=DBUtil.getConnection();
     String sql="select * from admin where username=? and password=?";
     PreparedStatement ps=con.prepareStatement(sql);
     ps.setString(1,uname);
     ps.setString(2,pwd);
     ResultSet rs=ps.executeQuery();
     if(rs.next()){
       HttpSession session=req.getSession();
       session.setAttribute("admin",uname);
       res.sendRedirect(req.getContextPath() + "/admin/dashboard");

     }

     else{
         PrintWriter out=res.getWriter();
         out.println("<h1>Try Again</h1>");
         out.println("<a href='" + req.getContextPath() + "/admin_login.html'>Try Again</a>");


     }

   }
   catch(Exception e){
      e.printStackTrace();
   }

 }
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.sendRedirect(request.getContextPath() + "/admin_login.html");
}



}