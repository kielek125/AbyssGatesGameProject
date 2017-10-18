package com.messanger.firebase.malavero.abyssgates;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.messanger.firebase.malavero.abyssgates.FINAL.StaticMethods;


public class RegisterYourAccount extends Activity {

    private EditText emailET, loginET, passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_your_account);
        fillControls();
    }

    private void fillControls() {
        emailET = (EditText) findViewById(R.id.emailRegisterYourAccountEditText);
        loginET = (EditText) findViewById(R.id.loginRegisterYourAccountEditText);
        passwordET = (EditText) findViewById(R.id.passwordRegisterYourAccountEditText);
    }

    public void registerAccount(View view) {
        try {
            String email = emailET.getText().toString();
            String login = loginET.getText().toString();
            String password = passwordET.getText().toString();

            if (!StaticMethods.emailValid(email)) {
                StaticMethods.toastMessage("Invalid email", getApplicationContext());
                return;
            }
//TODO
//            if (!StaticMethods.loginValid(login) {
//                StaticMethods.toastMessage("Invalid email", getApplicationContext());
//                return;
//            }
//            if (!StaticMethods.passwordValid(login) {
//                StaticMethods.toastMessage("Invalid password", getApplicationContext());
//                return;
//            }
            if (checkIfAccountAlreadyExist()) {
                String messageDialog = "";
                if (checkEmailIfExist(email))
                    messageDialog = "This email is already used";
                if (checkLoginIfExist(login))
                    messageDialog = "This login is already used";

                AlertDialog.Builder alreadyExist = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                alreadyExist.setMessage(messageDialog)
                        .setTitle("Create account")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alreadyExist.create().show();
            }
            else{
                createAccount(email, login, password);
            }
        } catch (Exception ex) {
            StaticMethods.toastException(ex, this);
        }

    }

    private boolean checkIfAccountAlreadyExist() {
        //TODO
        return true;
    }

    private boolean checkEmailIfExist(String email) {
        //TODO
        return true;
    }

    private boolean checkLoginIfExist(String login) {
        //TODO
        return true;
    }

    private void createAccount(String email, String login, String password){
        //TODO
    }
}
