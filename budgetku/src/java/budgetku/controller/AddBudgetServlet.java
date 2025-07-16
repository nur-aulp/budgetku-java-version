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


@WebServlet("/add-budget")
public class AddBudgetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String kategori = request.getParameter("kategori");
        String totalStr = request.getParameter("total");
        Integer userId = (Integer) request.getSession().getAttribute("id_user");
        
        try {
            double total = Double.parseDouble(totalStr);
            
            Budget budget = new Budget();
            budget.setId_User(userId);
            budget.setKategori(kategori);
            budget.setTotalBudget(total);
            budget.save();
            
            // Redirect ke home servlet untuk menampilkan data
            response.sendRedirect("home"); 
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("home?error=true");
        }
    }
}
