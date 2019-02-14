package app.util;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Str {

    private static final int BIT_AMOUNT = 8;

    //  ======================== Copy String to Clipboard ========================
    public static void copy(String str){

        final Clipboard cb = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        content.putString(str);
        cb.setContent(content);
    }

    //  ======================== Randomize String ========================
    public static String randomize(){
        String str;

        int l_lim = 97; // ASCII Equivalent for lower limit
        int u_lim = 122; // ASCII Equiv for upper limit
        int str_length = 10; // length of the randomized String

        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < str_length; i++){
            int rand_lim = l_lim + (int)(rand.nextFloat() * (u_lim - l_lim + 1));
            sb.append((char) rand_lim);
        }
        str = sb.toString();
        return str;
    }

    //  ======================== Escape SQL ========================
    public static String escapeSQL(String str){
        int length = str.length(),
                nLength = length;

        for(int i = 0; i < length; i++){
            char c = str.charAt(i);

            switch (c){
                case '\\':
                case '\"':
                case '\'':
                case '\0':
                    nLength += 1;
                    break;
            }
        }
        if(length == nLength)
            return str;

        StringBuilder sb = new StringBuilder(nLength);
        for(int i = 0; i < length; i++){
            char c = str.charAt(i);

            switch (c){
                case '\\' : {
                    sb.append("\\\\");
                } break;
                case '\"': {
                    sb.append("\\\"");
                } break;
                case '\'': {
                    sb.append("\\\'");
                } break;
                case '\0': {
                    sb.append("\\\0");
                } break;
                default: {
                    sb.append(c);
                } break;
            }
        }
        return sb.toString();
    }

    //  ======================== Binary Convert ========================
    public static String bin(String ascii){

        final byte[] bytes = ascii.getBytes();

        StringBuilder str = new StringBuilder(bytes.length * 8);
        for (byte b : bytes){
            int val = b;
            for (int i = 0; i < BIT_AMOUNT; i++){
                str.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return str.toString();
    }

    //  ======================== ASCII Convert ========================
    public static String ascii(String bin){

        if(bin.length() % BIT_AMOUNT != 0) {
            String msg = "binary must be a multiple of " + BIT_AMOUNT;
            throw new IllegalArgumentException(msg);
        }

        final int BIN_LENGTH = bin.length();
        final int BUILDER_SIZE = BIN_LENGTH / 8;
        StringBuilder str = new StringBuilder(BUILDER_SIZE);

        for (int i = 0; i < BIN_LENGTH; i += BIT_AMOUNT){
            char charCode = (char) Integer.parseInt(bin.substring(i, i + BIT_AMOUNT), 2);
            str.append(charCode);
        }
        return str.toString();
    }

    //  ======================== Current Date ========================
    public static String currDate(){

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return df.format(date);
    }
}
