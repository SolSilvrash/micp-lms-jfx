package app.local.server;

import app.util.DB;
import app.util.Page;
import app.util.Str;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Request {

    public static void send(String fname, String mname, String lname, String extname,
                            String address, String contact, String remarks) throws SQLException, ClassNotFoundException {

        if(isUnique(fname, mname, lname, extname)){

            String sql = "INSERT INTO student_pending (fname, mname, lname, extname, address, contact, remarks) " +
                    "VALUES (" +
                    "'" + Str.escapeSQL(fname) + "'," + "'" + Str.escapeSQL(mname) + "'," + "'" + Str.escapeSQL(lname) + "'," +  "'" + Str.escapeSQL(extname) + "'," + "'" + Str.escapeSQL(address) + "'," +
                    "'" + Str.escapeSQL(contact) + "'," + "'" + Str.escapeSQL(remarks) +
                    "')";

            System.out.println("REQUEST QUERY : " + sql);

            DB.dbExecuteUpdate(sql);

        } else {
            Page.errCode = 3;
            if(Page.errCode == 3){
                Page.error(
                        "SENDING REQUEST FAILED",
                        "Duplicate Requests Exception",
                        "The same student has already requested once.\n" +
                                "Please wait for the administrator to accept your request.");
            }
        }
    }

    private static boolean isUnique(String fname, String mname, String lname, String extname){

        String sql = "SELECT * FROM student_pending WHERE fname = '" + fname + "' AND lname = '" + lname + "' AND  mname = '" + mname + "' AND extname = '" + extname + "'";

        try {

            ResultSet rs = DB.dbExecuteQuery(sql);
            return !rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
