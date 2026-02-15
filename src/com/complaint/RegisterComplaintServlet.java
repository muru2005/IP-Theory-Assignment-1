package com.complaint;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

public class RegisterComplaintServlet extends HttpServlet{

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       String name=request.getParameter("name");
       String email=request.getParameter("email");
       String category=request.getParameter("category");
       String description=request.getParameter("description");
       response.setContentType("text/html");
       try{
           Connection con=DBUtil.getConnection();
           
           // Check for duplicates
           String checkSql = "SELECT count(*) FROM complaints WHERE email=? AND description=? AND status != 'Resolved'";
           PreparedStatement checkPs = con.prepareStatement(checkSql);
           checkPs.setString(1, email);
           checkPs.setString(2, description);
           java.sql.ResultSet checkRs = checkPs.executeQuery();
           if(checkRs.next() && checkRs.getInt(1) > 0) {
               PrintWriter out=response.getWriter();
               out.println("<html><head><title>Duplicate Complaint</title>");
               out.println("<link rel='stylesheet' type='text/css' href='css/style.css'>");
               out.println("</head><body>");
               out.println("<div class='container'>");
               out.println("<h1>Duplicate Complaint!</h1>");
               out.println("<p>We found a similar complaint from you that is still active.</p>");
               out.println("<a href='index.html' class='btn'>Go Home</a>");
               out.println("</div></body></html>");
               return;
           }

           String sql="INSERT into complaints(name,email,category,description,created_at) values(?,?,?,?,NOW())";
           PreparedStatement ps=con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
           ps.setString(1,name);
           ps.setString(2,email);
           ps.setString(3,category);
           ps.setString(4,description);
           ps.executeUpdate();
           java.sql.ResultSet rs = ps.getGeneratedKeys();
           if(rs.next()){
               int id = rs.getInt(1);
               PrintWriter out=response.getWriter();
               out.println("<html>");
               out.println("<head>");
               out.println("<title>Complaint Submitted</title>");
               out.println("<link rel='stylesheet' type='text/css' href='css/style.css'>");
               out.println("</head>");
               out.println("<body>");
               out.println("<div class='container'>");
               out.println("<h1>Complaint Submitted Successfully!</h1>");
               out.println("<h3>Your Complaint ID is: <span style='color: #e74c3c;'>" + id + "</span></h3>");
               out.println("<p>Please save this ID to track your complaint status.</p>");
               out.println("<a href='index.html' class='btn'>Go Home</a>");
               out.println("</div>");
               out.println("</body>");
               out.println("</html>");
           }
       }
       catch(Exception e){
        e.printStackTrace();
       }
  }

}
