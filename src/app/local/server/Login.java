package app.local.server;

import app.local.obj.Account;
import app.util.Page;
import app.util.DB;
import app.util.Encrypt;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    private static boolean isValid = false;

    public static Account login(String account_id, String password) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM account WHERE account_id = '" + account_id + "' AND password = '" + Encrypt.encryptPassword(password) + "'";

        try {

            ResultSet rs = DB.dbExecuteQuery(sql);

            Account acc = getAccountInfo(rs);

            if(acc != null)
                isValid = true;

            return acc;

        } catch (SQLException | ClassNotFoundException e) {

            System.out.println("An error occurs when traversing through accounts. Error: " + e);

            System.out.println("Error: " + Page.errCode);
            if(!(Page.errCode == 0 || Page.errCode == 1 || Page.errCode == 2)){
                Page.errCode = 3;
                Page.error(
                        "LOGIN FAILED",
                        "Account Lookup Failed",
                        "No such account is found in our database. Please contact the administrator for " +
                                "your credentials. \n" +
                                "Check if your password is correct."
                );
            }
            throw e;
        }
    }

    private static Account getAccountInfo(ResultSet rs) throws SQLException {

        Account acc = null;

        if(rs.next()){

            acc = new Account();

            acc.setAccount_id(rs.getString("account_id"));
            acc.setPassword(rs.getString("password"));
            acc.setAccount_type(rs.getString("account_type"));
        }
        return acc;
    }

    public static boolean isAccountValid(){
        return isValid;
    }

}
