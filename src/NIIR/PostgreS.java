package NIIR;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreS {

    public static void createBD() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "123");
            stmt = c.createStatement();
            String sql = "CREATE TABLE postgres" +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " str           TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void addBD(String s) {
        String z = "";
        Connection c1 = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c1 = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "123");

            c1.setAutoCommit(false);
            stmt = c1.createStatement();
            // String sql = "INSERT into postgres(id,str) values (1,'" + s + "')";
            String sql = "UPDATE postgres set str = '" + s + "' where id =1;";
            stmt.executeUpdate(sql);

            stmt.close();
            c1.commit();
            c1.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public static String getBD() {
        String str = "";
        Connection c2 = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c2 = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "123");

            c2.setAutoCommit(false);
            stmt = c2.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM postgres;");
            while (rs.next()) {
                str = str + rs.getString("str");

            }
            stmt.close();
            c2.commit();
            c2.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return str;
    }


}


