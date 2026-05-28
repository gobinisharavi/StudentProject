package project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      
        response.setIntHeader("Refresh", 10);
        response.setContentType("text/html");


        HttpSession session = request.getSession(false);

  
        if (session == null) {
            response.sendRedirect("index.html");
            return;
        }

      
        String name = (String) session.getAttribute("name");
        String course = (String) session.getAttribute("course");
        String age = (String) session.getAttribute("age");

      
        Date createTime = new Date(session.getCreationTime());
        Date lastAccess = new Date(session.getLastAccessedTime());

    
        String welcomeMsg = session.isNew() ? "Hello, Welcome!" : "Hello, Welcome Back!";

  
        String cookieName = "Not found";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("studentName")) {
                    cookieName = c.getValue();
                }
            }
        }

        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String currentTime = sdf.format(new Date());

      
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Dashboard</title>");
        out.println("<style>");
        out.println("body { font-family: Arial; background: #f0f0f0; padding: 30px; }");
        out.println(".card { background: white; padding: 30px; border-radius: 10px; ");
        out.println("        box-shadow: 0 0 10px rgba(0,0,0,0.2); max-width: 500px; margin: auto; }");
        out.println("h2 { color: #4CAF50; }");
        out.println("p { font-size: 16px; }");
        out.println(".label { font-weight: bold; color: #333; }");
        out.println(".time { color: #888; font-size: 13px; }");
        out.println("a { display: inline-block; margin-top: 20px; padding: 10px 20px;");
        out.println("    background: red; color: white; border-radius: 5px; text-decoration: none; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='card'>");
        out.println("<h2>" + welcomeMsg + " " + name + "</h2>");
        out.println("<p><span class='label'>Course:</span> " + course + "</p>");
        out.println("<p><span class='label'>Age:</span> " + age + "</p>");
        out.println("<hr/>");
        out.println("<p><span class='label'>Cookie Name Stored:</span> " + cookieName + "</p>");
        out.println("<p><span class='label'>Session Created:</span> " + createTime + "</p>");
        out.println("<p><span class='label'>Last Accessed:</span> " + lastAccess + "</p>");
        out.println("<hr/>");
        out.println("<p class='time'>Live Time (auto-refreshes every 10 sec): <b>" + currentTime + "</b></p>");
        out.println("<a href='LogoutServlet'>Logout</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
