package com.messanger.firebase.malavero.abyssgates.FINAL;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.messanger.firebase.malavero.abyssgates.R;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by kielson on 2017-10-18.
 */

public class StaticMethods {

    public static boolean emailValid(String email){
        String regExpn = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

        CharSequence emailValid = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailValid);

        if(matcher.matches() && email.length() > 6 && email.length() < 48)
            return true;
        else
            return false;
    }
    public static boolean loginValid(String login){
    int charCount = 0;
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_";
        if(login.length() > 8 && login.length() < 48){
            for(int i = 0; i < login.length(); i++){
                for(int j = 0; j < alphabet.length(); j++){
                    if(login.substring(i,i+1).equals(alphabet.substring(j,j+1))){
                        charCount++;
                    }
                }
            }
            int t = login.length();
            if(charCount != t){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
    public static void toastMessage(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    public static void toastException(Exception ex, Context context) {
        Toast.makeText(context, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
    }
    public static ImageView drawFlag(RelativeLayout layout, Context context, int high, int width, int leftMargin, int topMargin){
        float density = context.getResources().getDisplayMetrics().density;
        ImageView flag = new ImageView(context);
        RelativeLayout.LayoutParams params;
        flag.setImageResource(R.drawable.flag);
        params = new RelativeLayout.LayoutParams((int)(high * density),(int)(width * density));
        params.leftMargin = (int)(leftMargin * density);
        params.topMargin = (int)(topMargin * density);
        layout.addView(flag, params);
        return flag;
    }
}
