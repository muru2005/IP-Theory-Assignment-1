package com.complaint;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
public class UpdateComplaintServlet extends HttpServlet{
   protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
       HttpSession session=req.getSession(false);
       if(session==null || session.getAttribute("admin")==null){
        res.sendRedirect(req.getContextPath() + "/admin/login");
        return;
       }
       int id=Integer.parseInt(req.getParameter("id"));
       res.setContentType("text/html");
       PrintWriter out=res.getWriter();
       out.println("<h2>Update Complaint Status</h2>");
       out.println("<form method='post'>");
        out.println("<input type='hidden' name='id' value='" + id + "'>");
        out.println("Status: <select name='status'>");
        out.println("<option value='Pending'>Pending</option>");
        out.println("<option value='In Progress'>In Progress</option>");
        out.println("<option value='Resolved'>Resolved</option>");
        out.println("</select><br><br>");
        out.println("Remarks: <br><textarea name='remarks' rows='4' cols='50'></textarea><br><br>");
        out.println("<input type='submit' value='Update'>");
        out.println("</form>");
 

   }
  public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
      int id=Integer.parseInt(req.getParameter("id"));
      String status=req.getParameter("status");
      String remarks=req.getParameter("remarks");
      res.setContentType("text/html");
      try{
         Connection con=DBUtil.getConnection();
         PreparedStatement ps=con.prepareStatement("update complaints set status=?, remarks=? where complaint_id=?");
         ps.setString(1,status);
         ps.setString(2,remarks);
         ps.setInt(3,id);
         ps.executeUpdate();
         res.sendRedirect(req.getContextPath() + "/admin/dashboard");
      
      }
      catch(Exception e){
        e.printStackTrace();
      }

  }

}