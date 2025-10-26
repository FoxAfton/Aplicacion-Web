package com.mycompany.proyecto_u3.model;

import com.mycompany.proyecto_u3.model.User;
import java.sql.*;

public class UserDAO {
    private final String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=UserDB;encrypt=true;trustServerCertificate=true;";
    private final String jdbcUser = "sa";
    private final String jdbcPassword = "12345";

    public UserDAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
    }

    public boolean register(User user) {
        String sql = "INSERT INTO Users(username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(String emailOrUser, String password) {
        String sql = "SELECT id, username, email FROM Users WHERE (email = ? OR username = ?) AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emailOrUser);
            ps.setString(2, emailOrUser);
            ps.setString(3, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(String.valueOf(rs.getInt("id")), rs.getString("username"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
