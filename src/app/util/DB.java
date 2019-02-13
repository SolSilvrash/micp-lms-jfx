package app.util;

import app.util.config.DBConf;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    private static String connStr = "jdbc:mysql://" + DBConf.DB_HOST + "/" + DBConf.DB_NAME;

    private static void dbConnect() throws ClassNotFoundException, SQLException {

        try {
            Class.forName(DBConf.DB_DRIVER);
        } catch (ClassNotFoundException e){
            System.out.println("JDBC Driver not found. Make sure you have imported (mysql-connector-java-8.0.1) into your project library.");
            throw e;
        }

        System.out.println("MySQL JDBC Driver successfully registered ... ");

        try {
            Properties prop = new Properties();
            prop.setProperty("user", DBConf.DB_USER);
            prop.setProperty("password", DBConf.DB_PASS);
            prop.setProperty("useSSL", DBConf.DB_SSL);

            conn = DriverManager.getConnection(connStr, prop);
        } catch (SQLException e) {
            System.out.println("Connection failed. Output console : " + e);

            Page.errCode = 0;

            Page.error(
                    "DATABASE CONNECTION ERROR",
                    "Communications Link Error",
                    "Connection has not received any packets.\n" +
                            "Make sure MySQL is installed in your computer, " +
                            "and local server (WAMP, XAMPP, LAMPP) is currently running in your server."
            );

            throw e;
        }
    }

    private static void dbDisconnect() throws SQLException{
        if(conn != null && !conn.isClosed())
            conn.close();
    }

    public static ResultSet dbExecuteQuery(String query) throws SQLException, ClassNotFoundException{

        CachedRowSet crs;

        System.out.println("Select Statement : " + query + "\n");
        dbConnect();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            RowSetFactory factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();

            crs.populate(rs);
        } catch (SQLException e) {

            System.out.println("Problem Occurred at ExecuteQuery Operation : " + e);

            if(Page.errCode != 0) {

                Page.errCode = 1;
                Page.error(
                        "SQL QUERY STATEMENT ERROR",
                        "SQL Execute Query Error",
                        "Problem Occurred when trying to view data from the database."
                );
            }

            throw e;
        } finally {
            dbDisconnect();
        }

        return crs;
    }

    public static void dbExecuteUpdate(String query) throws SQLException, ClassNotFoundException {

        dbConnect();
        try (Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(query);

        } catch (SQLException e) {

            if (Page.errCode != 0) {

                System.out.println("Connection failed. Check output console : " + e);
                Page.error(
                        "SQL QUERY STATEMENT ERROR",
                        "SQL Update Query Error",
                        "Problem Occurred when trying to alter data from the database."
                );
            }

            throw e;
        } finally {
            dbDisconnect();
        }
    }

}
