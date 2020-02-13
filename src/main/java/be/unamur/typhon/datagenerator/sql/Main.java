package be.unamur.typhon.datagenerator.sql;

import be.unamur.typhon.datagenerator.generator.DataGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends DataGenerator {

    public static String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    public static String DB_URL = "jdbc:mysql://localhost:3306/RelationalDatabase";
    public static String USER = "root";
    public static String PASS = "example";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            int counter = 0;
            while (counter < 1000) {
                String sql = String.format("INSERT INTO `User`(`User.@id`, `User.id`, `User.login`, `User.firstName`, `User.lastName`, `User.street`, `User.number`, `User.city`, `User.postalCode`, `User.phoneNumber`, `User.emailAddress`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');", generateUuid(), generateRandomString(), generateRandomString(), generateRandomString(), generateRandomString(), generateRandomString(), generateRandomString(), generateRandomString(), generateRandomString(), generateRandomString(), generateRandomString());
                stmt.executeUpdate(sql);
                counter++;
                if (counter % 1000 == 0) {
                    conn.commit();
                }
            }
            if (counter % 1000 != 0)
                conn.commit();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
