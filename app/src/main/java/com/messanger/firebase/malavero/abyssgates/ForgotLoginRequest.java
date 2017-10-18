package com.messanger.firebase.malavero.abyssgates;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.messanger.firebase.malavero.abyssgates.FINAL.StaticMethods;

public class ForgotLoginRequest extends Activity {

    private EditText forgotEmail, forgotPassword;
    private ImageView passwordForgotImageView;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_login_request);

        forgotEmail = (EditText)findViewById(R.id.forgotEmailEditText);
        forgotPassword = (EditText)findViewById(R.id.forgotPasswordEditText);
        passwordForgotImageView = (ImageView)findViewById(R.id.forgotPasswordTextView);
        mode = getIntent().getIntExtra("MODE", 0);
        setDisplay();
    }
    public void forgotLoginRequest(View view){
        try {

            if (mode == 1) { //TODO przypomnienie hasła

            } else if (mode == 2) { //TODO przypomnienie loginu

            }
            //TODO jezeli email i haslo sie zgadzaja to wyslij login na maila
            String email = forgotEmail.getText().toString();
            if(!StaticMethods.emailValid(email)){
                StaticMethods.toastMessage("Invalid email", getApplicationContext());
                return;
            }
            String password = forgotPassword.getText().toString();
            //TODO połącz do bazy i sprawdz czy istnieją
            if (false && false) {
                StaticMethods.toastMessage("We sent to you your login", getApplicationContext());
                //TODO wyslij na podany adres przypomnienie z loginem
            } else {
                StaticMethods.toastMessage("Your email address or password is incorrect", getApplicationContext());
            }
        }
        catch (Exception e){
            StaticMethods.toastException(e, getApplicationContext());
        }
    }

    private void setDisplay(){
        if(mode == 1){
            passwordForgotImageView.setVisibility(View.INVISIBLE);
            forgotPassword.setVisibility(View.INVISIBLE);
        }
    }
}
