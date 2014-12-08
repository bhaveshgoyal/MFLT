package com.mflt.icici;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {


	private EditText loginf;
	private Button log;
	public static final String MyPREFERENCES = "MyPrefs" ; 
	public static final String pass2 = "passwordKey"; 
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
		}, 1000*60*4);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
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
			log.setText("Authenticate");
			log.setOnClickListener(new OnClickListener() {

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
		else if (!sharedpreferences.contains(pass2)){
			log.setText("Register");
			log.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					sharedpreferences=getSharedPreferences(MyPREFERENCES, 
							Context.MODE_PRIVATE);

					if (loginf.getText().toString().equals("")){
						Toast toasty = Toast.makeText(getApplicationContext(), "Password Can't Be Empty", Toast.LENGTH_SHORT);
						toasty.show();
					}
					else{
						login();
					}		
				}
			});

		}
		super.onResume();
	}


	public void login(){
		Editor editor = sharedpreferences.edit();
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
