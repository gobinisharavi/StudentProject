package project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String name = request.getParameter("name");
        String course = request.getParameter("course");
        String age = request.getParameter("age");

        
        Cookie nameCookie = new Cookie("studentName", name);
        nameCookie.setMaxAge(60 * 60 * 24); // 1 day
        response.addCookie(nameCookie);

      
        HttpSession session = request.getSession(true);
        session.setAttribute("name", name);
        session.setAttribute("course", course);
        session.setAttribute("age", age);

        
        response.sendRedirect("DashboardServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
