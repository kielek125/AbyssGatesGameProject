package com.messanger.firebase.malavero.abyssgates;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.messanger.firebase.malavero.abyssgates.FINAL.StaticMethods;
import com.messanger.firebase.malavero.abyssgates.FINAL.StaticStrings;


public class MainActivity extends Activity {


    private SharedPreferences spMySettings;
    private TextView termsAndConditionsTextView;
    private EditText loginEditText,passwordEditText;
    private CheckBox termsCheckBox;
    private CheckBox rememberSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            setContentView(R.layout.activity_main);
            fillControls();
            loadIfMySettingsHasValues();
        }
        catch(Exception e){
            StaticMethods.toastException(e, this);
        }
    }

    private void fillControls(){
        // tworzymy podkreślenie do stringa "terms and conditions"
        termsAndConditionsTextView = (TextView)this.findViewById(R.id.acceptTermsTextView);
        termsAndConditionsTextView.setPaintFlags(termsAndConditionsTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //bindujemy pozostale kontrolki
        loginEditText = (EditText)this.findViewById(R.id.loginEditText);
        passwordEditText = (EditText) this.findViewById(R.id.passwordEditText);
        termsCheckBox = (CheckBox) this.findViewById(R.id.acceptTermsCheckBox);
        rememberSettings = (CheckBox) this.findViewById(R.id.rememberSettings);

        //pobieramy shared preferences
        spMySettings = getSharedPreferences(StaticStrings.MY_SETTINGS, MODE_PRIVATE);
    }
    private void loadIfMySettingsHasValues(){
        String login = spMySettings.getString(StaticStrings.MY_LOGIN,"");
        String password = spMySettings.getString(StaticStrings.MY_PASSWORD, "");
        if(!login.equals("") && !password.equals("") && !login.isEmpty() && !password.isEmpty()){
            loginEditText.setText(login);
            passwordEditText.setText(password);
        }
    }
    private void saveIfCheckBoxIsChecked(){
        if(rememberSettings.isChecked()){
            String login = loginEditText.getText().toString();
            String password = loginEditText.getText().toString();
            SharedPreferences.Editor editorMySettings = spMySettings.edit();
            editorMySettings.putString(StaticStrings.MY_LOGIN, login);
            editorMySettings.putString(StaticStrings.MY_PASSWORD, password);
            editorMySettings.commit();
        }
    }

    public void loginToGame(View view) {
        //na początku jak sie uda to zapisz
        if(!termsCheckBox.isChecked()){
            AlertDialog.Builder notAgree = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
            notAgree.setMessage(StaticStrings.AGREE_TERMS)
                    .setTitle("Warning!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            notAgree.create().show();
        }
        else {
            //TODO
            saveIfCheckBoxIsChecked();
        }
    }

    public void forgotLogins(View view) {
        Intent forgotLogin = new Intent(this, ForgotLoginRequest.class);
        forgotLogin.putExtra("MODE", 2);
        startActivity(forgotLogin);
    }

    public void forgotPassword(View view) {
        Intent forgotPassword = new Intent(this, ForgotLoginRequest.class);
        forgotPassword.putExtra("MODE", 1);
        startActivity(forgotPassword);
    }

    public void showTermsAndConditions(View view) {
        String uri = getResources().getString(R.string.termsWebsite);
        Intent terms = new Intent(Intent.ACTION_VIEW);
        terms.setData(Uri.parse(uri));
        startActivity(terms);
    }

    public void register(View view) {
    }
}
