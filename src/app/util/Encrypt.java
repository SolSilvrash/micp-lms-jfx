package app.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

    private static String sha1(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA1");
        byte[] res = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();

        for (byte re : res)
            sb.append(Integer.toString((re & 0xff) + 0x100, 16).substring(1));

        return sb.toString();
    }

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        return (sha1(password) + sha1("silvrash")).substring(0,10);
    }

}
