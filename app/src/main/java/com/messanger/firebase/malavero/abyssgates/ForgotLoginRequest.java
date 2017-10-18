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

        forgotEmail = (EditText) findViewById(R.id.forgotEmailEditText);
        forgotPassword = (EditText) findViewById(R.id.forgotPasswordEditText);
        passwordForgotImageView = (ImageView) findViewById(R.id.forgotPasswordTextView);
        mode = getIntent().getIntExtra("MODE", 0);
        setDisplay();
    }

    public void forgotLoginRequest(View view) {
        try {
            String email = forgotEmail.getText().toString();
            String password = forgotPassword.getText().toString();
            if (email.equals("")) {
                StaticMethods.toastMessage("Enter your email", getApplicationContext());
                return;
            }
            if(mode == 2)
                if (password.equals("")) {
                    StaticMethods.toastMessage("Enter your password", getApplicationContext());
                    return;
                }
            if (mode == 1) { //przypomnienie hasła
                recoverPassword(email);
            } else if (mode == 2) { //przypomnienie loginu
                recoverLogin(email, password);
            }
            if (!StaticMethods.emailValid(email)) {
                StaticMethods.toastMessage("Invalid email", getApplicationContext());
                return;
            }
        } catch (Exception e) {
            StaticMethods.toastException(e, getApplicationContext());
        }
    }

    private void setDisplay() {
        if (mode == 1) {
            passwordForgotImageView.setVisibility(View.INVISIBLE);
            forgotPassword.setVisibility(View.INVISIBLE);
        }
    }

    private void recoverPassword(String email) {

        if (checkIfEmailExist(email)) { //- proponuje zrobic klase do obslugi połączeń zamiast pisać 15 razy tą samą metode
            sendNewPasswordToUserEmail(email);
        } else {
            StaticMethods.toastMessage("Email does not exist", getApplicationContext());
        }
    }

    private void recoverLogin(String email, String password) {
        if (checkIfEmailExist(password)) { // - jak wyżej
            if (checkPasswordIfExist(email)) {
                sendUserLoginToUserEmail(email);
            } else {
                StaticMethods.toastMessage("Login does not exist", getApplicationContext());
            }
        } else {
            StaticMethods.toastMessage("Email does not exist", getApplicationContext());
        }
    }

    private boolean checkIfEmailExist(String email) {
        //TODO
        return true;
    }
    private boolean checkPasswordIfExist(String password) {
        //TODO
        return true;
    }
    private void sendNewPasswordToUserEmail(String email){
        //TODO
    }
    private void sendUserLoginToUserEmail(String email){
        //TODO
    }
    //TODO po wysłaniu niech sie pokaze dialog z informacja oraz niech aktivity sie zamknie
}
