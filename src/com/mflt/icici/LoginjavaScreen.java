package com.mflt.icici;

/**
 * Created by gv on 7/16/2015.
 */

import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.Attributes;

import android.R.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginjavaScreen extends Activity {


    private EditText loginf;

    private EditText username;
    private EditText dob;

    private Button log;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                finish();
            }
        }, 1000*60*2);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);

        SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);
        if (!pref.getString("saved_num", "").equals("")){
            final long time_to_clean = pref.getLong("rem_time", Long.parseLong("60000"));

            new CountDownTimer(time_to_clean, 1000) {

                SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();

                public void onTick(long millisUntilFinished) {
                    editor.putLong("rem_time", millisUntilFinished);
                    editor.commit();
                    SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);

                    System.out.println("tine: " + pref.getLong("rem_time", Long.parseLong("0")));

                }

                public void onFinish() {
                    editor.putString("saved_num", "");
                    editor.putString("saved_amnt","");
                    editor.remove("rem_time");
                    editor.commit();
                }
            }.start();
        }

        log = (Button)findViewById(R.id.sign);
        log.setTextColor(Color.WHITE);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.otf");
        Typeface fontb = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.otf");
        log.setTypeface(font);

        loginf = (EditText)findViewById(R.id.putsign);
        loginf.setTextColor(Color.WHITE);
        loginf.setSelected(false);
        loginf.setTypeface(fontb);
        loginf.setHintTextColor(Color.LTGRAY);

    }

    @Override
    protected void onResume() {
        sharedpreferences=getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        if(sharedpreferences.contains(pass2) && !sharedpreferences.getAll().get(pass2).equals("passwordKey")){
            System.out.println(sharedpreferences.getAll().get(pass2));
            loginf.setText("");
            log.setText("Login");
            log.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    sharedpreferences=getSharedPreferences(MyPREFERENCES,
                            Context.MODE_PRIVATE);

                    if (loginf.getText().toString().equals("")){
                        Toast toasty = Toast.makeText(getApplicationContext(), "Password Can't Be Empty", Toast.LENGTH_SHORT);
                        toasty.show();
                    }
                    else{
                        if (sharedpreferences.getAll().get(pass2).equals(loginf.getText().toString())){
                            SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
                            editor.putBoolean("Login_session", true);
                            editor.commit();
                            String welcome = sharedpreferences.getAll().get(pass3).toString();
                            Toast toasty = Toast.makeText(getApplicationContext(),"welcome", Toast.LENGTH_SHORT);
                            toasty.show();
                            Intent i = new Intent(getApplicationContext(),com.mflt.icici.
                                    CardsActivity.class);
                            startActivity(i);
                        }
                        else{
                            Toast inc = Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT);
                            inc.show();
                        }
                    }
                }
            });
        }

        super.onResume();
    }


    public void login(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String p = loginf.getText().toString();
        editor.putString(pass2, p);
        editor.commit();
        Intent i = new Intent(this,com.mflt.icici.CardsActivity.class);
        Toast toast2 = Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT);
        toast2.show();
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
