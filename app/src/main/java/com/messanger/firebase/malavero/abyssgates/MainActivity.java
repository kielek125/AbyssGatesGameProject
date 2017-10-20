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
    private EditText loginEditText, passwordEditText;
    private CheckBox termsCheckBox, rememberSettingsCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            fillControls();
            loadIfMySettingsHasValues();
        } catch (Exception e) {
            StaticMethods.toastException(e, this);
        }
    }

    private void fillControls() {
        termsAndConditionsTextView = (TextView) this.findViewById(R.id.acceptTermsTextView);
        termsAndConditionsTextView.setPaintFlags(termsAndConditionsTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        loginEditText = (EditText) this.findViewById(R.id.loginEditText);
        passwordEditText = (EditText) this.findViewById(R.id.passwordEditText);
        termsCheckBox = (CheckBox) this.findViewById(R.id.acceptTermsCheckBox);
        rememberSettingsCheckBox = (CheckBox) this.findViewById(R.id.rememberSettings);

        spMySettings = getSharedPreferences(StaticStrings.MY_SETTINGS, MODE_PRIVATE);
    }

    private void loadIfMySettingsHasValues() {
        String login = spMySettings.getString(StaticStrings.MY_LOGIN, "");
        String password = spMySettings.getString(StaticStrings.MY_PASSWORD, "");
        boolean settings = spMySettings.getBoolean(StaticStrings.MY_CHECKED_SETTINGS, false);
        boolean terms = spMySettings.getBoolean(StaticStrings.MY_TERMS, false);
        loginEditText.setText(login);
        passwordEditText.setText(password);
        rememberSettingsCheckBox.setChecked(settings);
        termsCheckBox.setChecked(terms);
    }

    private void saveIfCheckBoxIsChecked() {
        if (rememberSettingsCheckBox.isChecked()) {
            String login = loginEditText.getText().toString();
            String password = loginEditText.getText().toString();
            boolean settings = termsCheckBox.isChecked();
            boolean terms = rememberSettingsCheckBox.isChecked();

            SharedPreferences.Editor editorMySettings = spMySettings.edit();
            editorMySettings.putString(StaticStrings.MY_LOGIN, login);
            editorMySettings.putString(StaticStrings.MY_PASSWORD, password);
            editorMySettings.putBoolean(StaticStrings.MY_CHECKED_SETTINGS, settings);
            editorMySettings.putBoolean(StaticStrings.MY_TERMS, terms);
            editorMySettings.commit();
        }
    }

    public void loginToGame(View view) {
        //na początku jak sie uda to zapisz
        if (!termsCheckBox.isChecked()) {
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
        } else {
            if(userCanLogin()){
                if(!userAlreadyHasPlanet()) {
                    startActivity(new Intent(this, ChoosePlanetActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), PlanetActivity.class));
                }
                saveIfCheckBoxIsChecked();
            }
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
        Intent registerIntent = new Intent(this, RegisterYourAccount.class);
        startActivity(registerIntent);
    }
    private boolean userCanLogin(){
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        return true;
    }
    private boolean userAlreadyHasPlanet(){
        //TODO
        return false;
    }
}
