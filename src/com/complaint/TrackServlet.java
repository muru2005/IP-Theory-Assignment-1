package com.complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class TrackServlet extends HttpServlet{
  protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
     String complaintId=req.getParameter("id");
      res.setContentType("text/html");
     PrintWriter out=res.getWriter();
     try{
      Connection con=DBUtil.getConnection();
      String sql="select name,status,description,remarks from complaints where complaint_id=?";
      PreparedStatement ps=con.prepareStatement(sql);
      ps.setInt(1,Integer.parseInt(complaintId));
      ResultSet rs=ps.executeQuery();
      
      out.println("<html><head><title>Complaint Details</title>");
       out.println("<link rel='stylesheet' type='text/css' href='css/style.css'>");
      out.println("</head><body>");
       out.println("<div class='container'>");
       
      if(rs.next()){
         String status = rs.getString("status");
         String remarks = rs.getString("remarks");
         String statusClass = "status-pending"; // default
         if("In Progress".equalsIgnoreCase(status)) statusClass = "status-progress";
         else if("Resolved".equalsIgnoreCase(status)) statusClass = "status-resolved";
          
         out.println("<h2>Complaint Details</h2>");
         out.println("<div class='details-box'>");
         
         out.println("<div class='details-row'>");
         out.println("<span class='details-label'>Complainant Name:</span>");
         out.println("<span class='details-value'>" + rs.getString("name") + "</span>");
         out.println("</div>");
         
         out.println("<div class='details-row'>");
         out.println("<span class='details-label'>Description:</span>");
         out.println("<span class='details-value'>" + rs.getString("description") + "</span>");
         out.println("</div>");
         
         out.println("<div class='details-row'>");
         out.println("<span class='details-label'>Current Status:</span>");
         out.println("<span class='status-badge " + statusClass + "'>" + status + "</span>");
         out.println("</div>");

         if(remarks != null && !remarks.trim().isEmpty()){
            out.println("<div class='details-row'>");
            out.println("<span class='details-label'>Admin Remarks:</span>");
            out.println("<span class='details-value'>" + remarks + "</span>");
            out.println("</div>");
         }
         
         out.println("</div>"); // details-box
         out.println("<br>");
         out.println("<a href='track.html' class='btn'>Track Another</a>");

      }
      else{
        out.println("<h3>No complaint found with ID: " + complaintId + "</h3>");
        out.println("<a href='track.html' class='btn'>Try Again</a>");
      }
      out.println("</div></body></html>"); // container, body, html
 
     }
     catch(Exception e){
        e.printStackTrace();
     }

  }
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.sendRedirect(request.getContextPath() + "/track.html"); 
    }

}
