package budgetku.controller;

import budgetku.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        boolean isValid = user.login(username, password);

        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("id_user", user.id_User);
            session.setAttribute("username", user.username);
            response.sendRedirect("home");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
