package budgetku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import budgetku.DbConnection;

public class User {
    public int id_User;
    public String username;
    
    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_user) {
        this.id_User = id_user;
    }
    
    public boolean login(String username, String password){
        Connection conn = DbConnection.getConnection();
        String sql = "SELECT * FROM users WHERE username = ? AND password = MD5(?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.id_User = rs.getInt("id_User"); // ambil ID user
                this.username = rs.getString("username");
                conn.close();
                return true;
            }
            conn.close();
            return false;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean register(String password){
        Connection conn = DbConnection.getConnection();
        String sql = "INSERT INTO users (username,password) values (?,MD5(?))";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            
            conn.close();

            return true;
        
        }catch (Exception ex) {
            return false;
        }
    }
}
