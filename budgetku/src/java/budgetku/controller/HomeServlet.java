package budgetku.controller;

import budgetku.Budget;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("id_user");
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        ArrayList<Budget> budgetList = Budget.getList(userId);
        request.setAttribute("list", budgetList);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
