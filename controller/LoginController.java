package controller;

import dao.UserDAO;
import model.User;
import view.LoginView;

import java.sql.Connection;

public class LoginController {
    private final Connection conn;
    private final UserDAO userDAO;
    private final LoginView loginView;

    public LoginController(Connection conn) {
        this.conn = conn;
        this.userDAO = new UserDAO(conn);
        this.loginView = new LoginView();
    }

    public User login() {
        try {
            String[] credentials = loginView.getLoginCredentials();
            String username = credentials[0];
            String password = credentials[1];

            User user = userDAO.validateUser(username, password);

            if (user == null) {
                System.out.println("❌ Invalid username or password.");
            }
            return user;

        } catch (Exception e) {
            System.out.println("⚠️ Login failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
