package com.complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class AdminDashboardServlet extends HttpServlet{
   protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
      HttpSession session= req.getSession(false);
      if(session==null||session.getAttribute("admin")==null){
        res.sendRedirect(req.getContextPath() + "/admin/login");
        return;
      }
      res.setContentType("text/html");
      PrintWriter out=res.getWriter();
      out.println("<h2>Admin Dashboard</h2>");
       out.println("<a href='" + req.getContextPath() + "/admin/logout'>Logout</a><br><br>");
      try{
        Connection con=DBUtil.getConnection();
        String sql="select * from complaints";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Email</th><th>Category</th><th>Description</th><th>Status</th><th>Remarks</th><th>Action</th></tr>");
        while(rs.next()){
            int id=rs.getInt("complaint_id");
            String name=rs.getString("name");
            String email=rs.getString("email");
            String category=rs.getString("category");
            String description=rs.getString("description");
            String status=rs.getString("status");
            String remarks=rs.getString("remarks");
            out.println("<tr>");
            out.println("<td>"+id+"</td>");
            out.println("<td>"+name+"</td>");
            out.println("<td>"+email+"</td>");
            out.println("<td>"+category+"</td>");
            out.println("<td>"+description+"</td>");
            out.println("<td>"+status+"</td>"); 
            out.println("<td>"+(remarks != null ? remarks : "")+"</td>"); 
            out.println("<td><a href='" + req.getContextPath() + "/admin/update?id="+id+"'>Update Status</a></td>");   
            out.println("</tr>");

        }
        out.println("</table>");

      }
      catch(Exception e){
        e.printStackTrace();
      }

   }


}
