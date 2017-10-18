package com.messanger.firebase.malavero.abyssgates;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotLoginRequest extends Activity {

    private EditText forgotEmail, forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_login_request);

        forgotEmail = (EditText)findViewById(R.id.forgotEmailEditText);
        forgotPassword = (EditText)findViewById(R.id.forgotPasswordEditText);
    }
    public void forgotLoginRequest(View view){
        //TODO jezeli email i haslo sie zgadzaja to wyslij login na maila
        String email = forgotEmail.getText().toString();
        String password = forgotPassword.getText().toString();
        //TODO połącz do bazy i sprawdz czy istnieją
        if(false && false){
            Toast.makeText(getApplicationContext(), "We sent to you your login", Toast.LENGTH_LONG).show();
            //TODO wyslij na podany adres przypomnienie z loginem
        }
        else{
            Toast.makeText(getApplicationContext(), "Your email address or password is incorrect", Toast.LENGTH_LONG).show();
        }
    }
}
