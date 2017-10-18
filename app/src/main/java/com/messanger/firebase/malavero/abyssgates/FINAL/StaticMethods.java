package com.messanger.firebase.malavero.abyssgates.FINAL;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kielson on 2017-10-18.
 */

public class StaticMethods {

    public static boolean emailValid(String email){
        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence emailValid = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailValid);

        if(matcher.matches())
            return true;
        else
            return false;
    }
    public static void toastMessage(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    public static void toastException(Exception ex, Context context) {
        Toast.makeText(context, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
    }
}
