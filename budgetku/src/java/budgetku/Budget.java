package budgetku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import budgetku.DbConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Budget {
    private int id_User;
    public int id_Budget;
    private String kategori;
    private double total_budget;
    public LocalDateTime waktu;
    
    public void setId_User(int id_user){
        this.id_User = id_user;
    }
    
    public void setKategori(String kategori){
        this.kategori = kategori;
    }
    
    public void setTotalBudget(double total_budget) {
        this.total_budget = total_budget;
    }
    
    public double getTotalBudget() {
        return total_budget;
    }
    
    public String getKategori() {
        return kategori;
    }
    
    public double getTotal_budget() {
        return total_budget;
    }

    
    public void save() throws Exception{
        Connection conn = DbConnection.getConnection();
        String sql = "INSERT INTO budgets (id_user, kategori, total_budget) VALUES (?,?,?)";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_User);
        ps.setString(2, kategori);
        ps.setDouble(3, total_budget);
        
        ps.executeUpdate();
            
        conn.close();
    }
    
    public static ArrayList<Budget> getList(int userId) {
        Connection conn = DbConnection.getConnection();
        String sql = "SELECT * FROM budgets WHERE id_user = ?";
        ArrayList<Budget> result = new ArrayList<>();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Budget entry = new Budget();
                entry.id_Budget = rs.getInt("id_budget");
                entry.id_User = rs.getInt("id_user");
                entry.kategori = rs.getString("kategori");
                entry.total_budget = rs.getDouble("total_budget");
                entry.waktu = rs.getTimestamp("waktu") != null ? rs.getTimestamp("waktu").toLocalDateTime() : null;

                result.add(entry);
            }
            
            conn.close();
            return result;
            
        }catch (Exception ex) {
            ex.printStackTrace(); 
            return new ArrayList<>();
        }
    }
    
}
