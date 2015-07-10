package com.mflt.icici;

import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

@SuppressLint({ "ResourceAsColor", "UseValueOf" })
public class LightSensor extends Activity {
	Button button0;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;
	Button button8;
	Button button9;
	Button buttonb;
	Button buttont;
	FloatingActionButton fab_cancel;
	Button saved_float;
	ImageView pin0;
	ImageView pin1;
	ImageView pin2;
	ImageView pin3;
	boolean cancel = false;
	boolean start = false;
	private String pcode="";
	private String pcode_backup="";
	private boolean isLighOn = false;
	String fld = new String();
	Timer t, logt;
	String num = new String();
	String[] bin=null;
	String tempstr = new String();
	Timer timer;
	Bundle b;
	String nos = new String("nos");
	static Camera camera=null;
	static boolean hasflash = false;
	String key = new String("key");
	String amount = new String("0");
	String newpin = new String();
	String dialog_option;
	private Button button;
	static LinearLayout screen;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		bin = null;
		logt = new Timer();
		logt.schedule(new TimerTask() {

			@Override
			public void run() {
				finish();
			}
		}, 1000*60*2);
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.putBoolean("Login_session", true);
		editor.commit();
		
		screen = (LinearLayout)findViewById(R.id.screen_trans);
		hasflash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
		//		hasflash = false;
		if (hasflash){
			camera = Camera.open();
		}
		b = this.getIntent().getExtras();
		setContentView(R.layout.activity_light_sensor);
		//
		//		button = (Button) findViewById(R.id.toggle);
		Context context = this;
		PackageManager pm = context.getPackageManager();

		// if device support camera?
		if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Log.e("err", "Device has no camera!");
			return;
		}
		Typeface xpressive = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.otf");

		button0 = (Button)findViewById(R.id.button0);
		button0.setTypeface(xpressive);
		button0.setTextColor(Color.WHITE);
		//		button0.setOnClickListener(pinButtonHandler);
		button1 = (Button)findViewById(R.id.button1);
		button1.setTextColor(Color.WHITE);
		button1.setTypeface(xpressive);
		//		button1.setOnClickListener(pinButtonHandler);
		button2 = (Button)findViewById(R.id.button2);
		button2.setTextColor(Color.WHITE);
		button2.setTypeface(xpressive);
		//		button2.setOnClickListener(pinButtonHandler);
		button3 = (Button)findViewById(R.id.button3);
		button3.setTextColor(Color.WHITE);
		button3.setTypeface(xpressive);
		//		button3.setOnClickListener(pinButtonHandler);
		button4 = (Button)findViewById(R.id.button4);
		button4.setTextColor(Color.WHITE);
		button4.setTypeface(xpressive);
		//		button4.setOnClickListener(pinButtonHandler);
		button5 = (Button)findViewById(R.id.button5);
		button5.setTextColor(Color.WHITE);
		button5.setTypeface(xpressive);
		//		button5.setOnClickListener(pinButtonHandler);
		button6 = (Button)findViewById(R.id.button6);
		button6.setTextColor(Color.WHITE);
		button6.setTypeface(xpressive);
		//		button6.setOnClickListener(pinButtonHandler);
		button7 = (Button)findViewById(R.id.button7);
		button7.setTextColor(Color.WHITE);
		button7.setTypeface(xpressive);
		//		button7.setOnClickListener(pinButtonHandler);
		button8 = (Button)findViewById(R.id.button8);
		button8.setTextColor(Color.WHITE);
		button8.setTypeface(xpressive);
		//				OnClickListener pinButtonHandler;
		//				button8.setOnClickListener(pinButtonHandler);
		button9 = (Button)findViewById(R.id.button9);
		button9.setTextColor(Color.WHITE);
		button9.setTypeface(xpressive);


		buttont = (Button)findViewById(R.id.buttont);
		buttont.setTextColor(Color.WHITE);
		buttont.setTypeface(xpressive);

		buttonb = (Button)findViewById(R.id.buttonb);
		//		buttonb.setBackgroundColor(R.color.nexusgrey);
		buttonb.setTextColor(Color.WHITE);
		buttonb.setTypeface(xpressive);


		//		ImageButton pin0 = (ImageButton)findViewById(R.id.pin0);
		//		pin0.setImageResource(R.drawable.dollarf);


		button0.setOnClickListener(pinButtonHandler);
		button1.setOnClickListener(pinButtonHandler);
		button2.setOnClickListener(pinButtonHandler);

		button3.setOnClickListener(pinButtonHandler);

		button4.setOnClickListener(pinButtonHandler);
		button5.setOnClickListener(pinButtonHandler);
		button6.setOnClickListener(pinButtonHandler);
		button7.setOnClickListener(pinButtonHandler);
		button8.setOnClickListener(pinButtonHandler);
		buttonb.setOnClickListener(pinButtonHandler);

		button9.setOnClickListener(pinButtonHandler);
		
		Drawable img = getApplicationContext().getResources().getDrawable( R.drawable.disable);
		buttont.setText("");
		img.setBounds( 0, 0, 98, 98 );
		buttont.setCompoundDrawables( img, null, null, null );
		buttonb.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);
				if (pref.getString("saved_num","") != ""){
				SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
				
				editor.putString("pcode_backup","");
				editor.putString("saved_num","");
				editor.commit();
				Toast.makeText(LightSensor.this, "Saved Session Successfully Cleared", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(LightSensor.this, "No Transaction is Currently Saved", Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		
		SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);
		
		
		if (!pref.getString("saved_num", "").equals("") && !b.getBoolean("from_saved",false)){
			
			
			final long time_to_clean = pref.getLong("rem_time", Long.parseLong("60000"));

			new CountDownTimer(time_to_clean, 1000) {
			
			SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
			
		     public void onTick(long millisUntilFinished) {
		    	 editor.putLong("rem_time",millisUntilFinished);
		    	 editor.commit();
				 SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);

				 System.out.println("tine: " + millisUntilFinished);
		     }

		     public void onFinish() {
		    	editor.putString("saved_num", "");
				editor.putString("saved_amnt","");
				 editor.remove("rem_time");
				editor.commit();
		     }
		  }.start();
		}
		
		if (b.getBoolean("from_saved",false) && start == false)
		{
			int pos = b.getInt(key);
			String show_num = "";
			String shared_num = pref.getString("card_numb[" + pos + "]","");
			String save_amnt = pref.getString("saved_amnt","");
			String save_info = pref.getString("card_info[" + pos + "]","");
			
			for (int i = 0; i < shared_num.length() - 3; i++) {
				show_num += "*";
			}
			show_num += pref.getString("card_numb_last3[" + pos + "]","");
			
			TextView saved_num = (TextView)findViewById(R.id.card_last3_saved);
			saved_num.setVisibility(View.VISIBLE);
			saved_num.setText("From: "  + show_num);
			
			TextView saved_amnt = (TextView)findViewById(R.id.amnt_saved);
			saved_amnt.setVisibility(View.VISIBLE);
			saved_amnt.setText("Amount: "  + save_amnt);
			
			TextView saved_info = (TextView)findViewById(R.id.info_saved);
			saved_info.setVisibility(View.VISIBLE);
			saved_info.setText("Card Info: "  + save_info);
		}
		buttont.setOnClickListener(new OnClickListener() {

			final Parameters p = camera.getParameters();

			int mind = 0;

			@Override
			public void onClick(View arg0) {

//				System.out.println("flag: " + cancel_flag);
				if (start == true){
					cancel = true;
					trans();
				}
				
				SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);
				
				if (b.getBoolean("from_saved",false) && start == false)
				{
					
					if (pcode.length() == 0)
						Toast.makeText(LightSensor.this, "Please Input Pin", Toast.LENGTH_SHORT).show();
					else{
						pcode_backup = pref.getString("pcode_backup","");
						if (pcode.equals(pcode_backup)){
					start = true;
					num = pref.getString("saved_num","");
					trans_process();
					}
						else
							Toast.makeText(LightSensor.this, "Input Pin Doesn't Match With One in Saved Session.", Toast.LENGTH_LONG).show();
					}
				}
				else {
				int pos = b.getInt(key);
				mind = 0;
//				EditText field = (EditText)findViewById(R.id.lednum);
				num = "" + 7;
				SharedPreferences prefs = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE); 
				
				num += prefs.getString("card_numb["+ pos + "]","");
//				num += field.getText().toString();
				
				if (num.length() != 17 && prefs.getInt("clicks_card[" + pos + "]",0) == 1){
					Toast toaste = Toast.makeText(getApplicationContext(), "Card Number should be 16 digits", Toast.LENGTH_LONG);	
					toaste.show();
					//						if (camera != null){
					//							camera.release();
					//						}
					return;
					//						}	
				}
				// p = 3041
				// q == 11927
				// n = p*q = 36270007
				// e = 2621
				// cardnum ^ e mod n
//				BigInteger p = new BigInteger("3041");
//				BigInteger q = new BigInteger("11927");
				if (prefs.getString("card_numb[" + pos + "]","").length() == 16){
				BigInteger n = new BigInteger("10584631930058819");
				BigInteger e = new BigInteger("236184211");

				BigInteger enc_num = new BigInteger(prefs.getString("card_numb["+ pos + "]","")).modPow(e,n);
				SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
				
				editor.putString("card_numb["+ pos + "]",repeat("0",17-enc_num.toString().length()) + enc_num.toString());
				editor.commit();
				}
				num = "" + prefs.getString("card_numb["+ pos + "]","");
				
				
				//				    RSA rsa = new RSA(n,e);

				//				    int[] order = {12,3,7,5,16,14,9,1,8,13,4,11,15,10,6,2};
				//				    String[] jumbled = new String[16];
				//				    for (int i = 0; i < jumbled.length; i++) {
				//						if (order[i] == 13 || order[i] == 15 || order[i] == 14 || order[i] == 16){
				//							  BigInteger plain = new BigInteger(num.charAt(order[i]-1) + "");
				//							     jumbled[i] = plain.modPow(e, n).toString();
				//						}
				//						else
				//				    	jumbled[i] = num.charAt(order[i]-1) + ""; 
				//					}


				//				    num = "";
				//				    for (int i = 0; i < jumbled.length; i++) {
				//						num += jumbled[i];
				//					}

				//				    jumbled[0] = 
				//				RSA rsa = new RSA(1024);
				//
				//				String text1 = "Yellow and Black Border Collies";

				if (pcode.length() == 4)
				{
					SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
					editor.putString("pcode_backup",pcode);
					editor.commit();
					num = num + pcode;
				}
				else
				{
					Toast.makeText(LightSensor.this,"ATM Pin Should Be Exactly 4 Digits",Toast.LENGTH_LONG).show();
					return;
				}


//				String part_1 = num.substring(1,8);
//				String part_2 = num.substring(8,15);
//				String part_3 = num.substring(15,21);
//				BigInteger unenc_1 = new BigInteger(part_1);
//				String enc_1 = unenc_1.modPow(e,n).toString();
//				BigInteger unenc_2 = new BigInteger(part_2);
//				String enc_2 = unenc_2.modPow(e,n).toString();
//				BigInteger unenc_3 = new BigInteger(part_3);
//				String enc_3 = unenc_3.modPow(e,n).toString();

				String atm_part = num.substring(num.length() - 16 ,num.length());
				BigInteger unenc_atm = new BigInteger(atm_part);
				
				BigInteger n1 = new BigInteger("11442778700763419");
				BigInteger e1 = new BigInteger("233811181");
				
				String enc_atm = unenc_atm.modPow(e1,n1).toString();
				String final_enc = "" + 7 + num.substring(0,5) + repeat("0",17 - enc_atm.toString().length()) + enc_atm;

//				Toast.makeText(LightSensor.this,final_enc,Toast.LENGTH_LONG).show();
//				String final_enc = enc_1 + enc_2 + enc_3;


				num = final_enc;
				AlertDialog.Builder builderSingle = new AlertDialog.Builder(
						LightSensor.this);
				builderSingle.setIcon(R.drawable.ic_launcher);
				builderSingle.setTitle("Choose An Option");
				final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
						LightSensor.this,
						android.R.layout.select_dialog_singlechoice);
				arrayAdapter.add("Cash Withdraw");
				arrayAdapter.add("Check Balance");
				arrayAdapter.add("Change Pin");

				

				LayoutInflater factory = LayoutInflater.from(LightSensor.this);
				final View textEntryView = factory.inflate(R.layout.design_withdraw, null);  

				builderSingle.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						start = false;
						dialog.dismiss();
					}
				});

				builderSingle.setAdapter(arrayAdapter,
						new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String strName = arrayAdapter.getItem(which);
						AlertDialog.Builder builderInner = new AlertDialog.Builder(
								LightSensor.this);
						if (strName.equals("Cash Withdraw")){
							dialog_option = "1";
							Toast.makeText(LightSensor.this, "Please Enter the Amount in multiples of 100 only",Toast.LENGTH_LONG).show();
							builderInner.setMessage("Enter Withdrawl Amount (Click Withdraw when ready)");
							builderInner.setTitle("Cash Withdrawl");
							builderInner.setView(textEntryView);
							builderInner.setPositiveButton("Withdraw Now",
									new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialog,
										int which) {

									EditText mUserText;
									mUserText = (EditText) textEntryView.findViewById(R.id.with_amnt);
									if ( Integer.parseInt(mUserText.getText().toString()) % 100 != 0 ){
										Toast.makeText(LightSensor.this, "Amount should be in multiples of 100 only", Toast.LENGTH_SHORT).show();
										return;
									}
									else {
									amount = mUserText.getText().toString();
									dialog.dismiss();
									String amnt_parsed = repeat("0",5-amount.length()) + amount;
									num += dialog_option + amnt_parsed + "7";
									start = true;

									trans_process();
									}
								}
							});
							builderInner.setNegativeButton("Save For Later", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									EditText mUserText;
									mUserText = (EditText) textEntryView.findViewById(R.id.with_amnt);
									if ( Integer.parseInt(mUserText.getText().toString()) % 100 != 0 ){
										Toast.makeText(LightSensor.this, "Amount should be in multiples of 100 only", Toast.LENGTH_SHORT).show();
									}
									else{
									SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
									editor.putString("saved_num", num);
									editor.putString("saved_amnt",	mUserText.getText().toString());
									editor.commit();
//									pcode = "";
//									pin0 = (ImageView)findViewById(R.id.pin0);
//									pin0.setImageResource(R.drawable.dollar_empty2);
//									pin1 = (ImageView)findViewById(R.id.pin1);
//									pin1.setImageResource(R.drawable.dollar_empty2);
//									pin2 = (ImageView)findViewById(R.id.pin2);
//									pin2.setImageResource(R.drawable.dollar_empty2);
//									pin3 = (ImageView)findViewById(R.id.pin3);
//									pin3.setImageResource(R.drawable.dollar_empty2);
//									button0.setTextColor(Color.WHITE);
//									button0.setClickable(true);
//
//									button1.setTextColor(Color.WHITE);
//									button1.setClickable(true);
//
//									button2.setTextColor(Color.WHITE);
//									button2.setClickable(true);
//
//									button3.setTextColor(Color.WHITE);
//									button3.setClickable(true);
//
//									button4.setTextColor(Color.WHITE);
//									button4.setClickable(true);
//
//									button5.setTextColor(Color.WHITE);
//									button5.setClickable(true);
//
//									button6.setTextColor(Color.WHITE);
//									button6.setClickable(true);
//
//									button7.setTextColor(Color.WHITE);
//									button7.setClickable(true);
//
//									button8.setTextColor(Color.WHITE);
//									button8.setClickable(true);
//
//									button9.setTextColor(Color.WHITE);
//									button9.setClickable(true);
//
//									Drawable img = getApplicationContext().getResources().getDrawable( R.drawable.disable);
//									buttont.setText("");
//									img.setBounds( 0, 0, 98, 98 );
//									buttont.setCompoundDrawables( img, null, null, null );

									Toast.makeText(LightSensor.this, "Current Session Saved", Toast.LENGTH_SHORT).show();
									
									start = false;
									

									Intent i = new Intent(LightSensor.this,com.mflt.icici.CardsActivity.class);
									startActivity(i);	
									LightSensor.this.finish();
									}
								}
							});
						}
						else if (strName.equals("Check Balance")){
							dialog_option = "2";
							
							dialog.dismiss();
							String amnt_parsed = repeat("0",5-amount.length()) + amount;
							num += dialog_option + amnt_parsed + "7";
							start = true;
						
							trans_process();
						}
						else if (strName.equals("Change Pin")){
							dialog_option = "3";
							builderInner.setMessage("Enter New PIN (Click Change Pin when ready)");
							builderInner.setTitle("Change PIN");
							builderInner.setView(textEntryView);
							builderInner.setPositiveButton("Change Pin",
									new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialog,
										int which) {

									EditText mUserText;
									mUserText = (EditText) textEntryView.findViewById(R.id.with_amnt);
									newpin = mUserText.getText().toString();
									if (newpin.length() == 4){
									dialog.dismiss();
//									String pin_parsed = repeat("0",5-amount.length()) + amount;
									num += dialog_option + newpin + "7";
									start = true;
								
									trans_process();
									}
									else{
										Toast.makeText(LightSensor.this, "PIN Number Should be 4 Digits", Toast.LENGTH_SHORT).show();
									}
								}
							});
							builderInner.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									start = false;
									return;   
								}
							});

//							dialog.dismiss();
//							String amnt_parsed = repeat("0",5-amount.length()) + amount;
//							num += dialog_option + amnt_parsed + "7";
//							trans_process();
						}
						builderInner.show();
					}
				});
				builderSingle.show();

				

				//								num += "" + 3;
				//				System.out.print("Plaintext: " + numint);
				//				BigInteger plaintext = new BigInteger(numint.getBytes());
				//				//
				//				
				//				BigInteger ciphertext = rsa.encrypt(plaintext);
				//				String num		 = "" + ciphertext;
				////				System.out.print("Ciphertext: " + ciphertext);
				//				System.out.println(num);
				//				Toast toastte = Toast.makeText(getApplicationContext(), num, Toast.LENGTH_LONG);	
				//				toastte.show();
				//				
				//				plaintext = rsa.decrypt(ciphertext);
				//				//
				//				String text2 = new String(plaintext.toByteArray());
				////				System.out.print("Plaintext: " + text2);
			
				//				 mHandler.postDelayed(new Runnable() {
				//			            public void run() {
				//							trans();
				//			            }
				//			        }, 500);
				//			    timer.scheduleAtFixedRate(new TimerTask() {
				//
				//			        synchronized public void run() {
				//
				//
				//							if (isLighOn){
				//								p.setFlashMode(Parameters.FLASH_MODE_OFF);
				//								camera.setParameters(p);
				//								camera.stopPreview();
				//								isLighOn = false;
				//							}
				//			            if (camera != null){
				//			            	camera.release();
				//			            }
				//			            return;
				//
				//			        }}, delay*num.length()*4,);



				//				bin[0]="0";
				//				bin[1] ="0";
				//				bin[2] = "0";
				//				bin[3] ="1";
				}
			}

			public void trans_process() {
				String[] num2 = new String[num.length()];
				for (int tmp = 0; tmp < num.length(); tmp++) {
//					if (tmp % 2 == 1) {
//						num2[tmp - 1] = "" + 1;
//					} else {
						num2[tmp] = num.charAt(tmp) + "";
//					}
				}

//				EditText field = (EditText)findViewById(R.id.lednum);
				String disp = new String();
				for (int i = 0; i < num2.length; i++) {
					disp += num2[i] + "";
				}
				System.out.println("disp :" + disp);
//				field.setText(num2.length);
//				boolean flag = true;
				bin = new String[4 * num2.length];

				int j = 0;
				for (int i = 0; i < num2.length; i++) {
//					if (i % 2 == 0) {
//						bin[j++] = "0";
//						bin[j++] = "1";
//					} else {
						Integer temp = new Integer(Integer.parseInt(num2[i] + ""));

						tempstr = repeat("0", 4 - Integer.toBinaryString(temp).length()) + Integer.toBinaryString(temp);
						for (int index = 0; index < 4; index++) {
							bin[j++] = tempstr.charAt(index) + "";
//						}
					}

					//						for(int index = 0; index < 4; index++){
					//							
					//							if (index == 3){
					//								bin[j++] = "1";
					//							}
					//							bin[j++] =  "0";
					//							}

				}
System.out.println("bin:" + bin);

				//				Toast toasty = Toast.makeText(getApplicationContext(), disp, Toast.LENGTH_LONG);
				//				toasty.show();

				timer = new Timer();
//				EditText delfield = (EditText)findViewById(R.id.leddelay);
				int delay = 100;
//				if (delay.toString() == ""){
//					Toast toaste = Toast.makeText(getApplicationContext(), "Please Enter Frequency for Transmission", Toast.LENGTH_LONG);	
//					toaste.show();
//					//					if (camera != null){
//					//						camera.release();
//					//					}
//					return;
//				}

				Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.cancel);
				img.setBounds(0, 0, 98, 98);
				buttont.setText("");
				buttont.setCompoundDrawables(img, null, null, null);

				fab_cancel = (FloatingActionButton)findViewById(R.id.cancel_float);
				if (hasflash == true){
					RelativeLayout process_lay = (RelativeLayout) findViewById(R.id.process_layout);
					process_lay.setVisibility(View.VISIBLE);
					fab_cancel.setVisibility(View.VISIBLE);
				}
				fab_cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (hasflash == true) {
							if (isLighOn && camera != null) {
								p.setFlashMode(Parameters.FLASH_MODE_OFF);
								camera.setParameters(p);
								camera.stopPreview();
								isLighOn = false;
							}
						} else {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {

									LinearLayout temp = (LinearLayout) findViewById(R.id.screen_trans);
									temp.setVisibility(View.GONE);

								}
							});
							//						screen.setVisibility(View.GONE);
						}
						start = false;
						cancel = false;
						System.exit(0);
					}
				});

				timer.scheduleAtFixedRate(new TimerTask() {

					synchronized public void run() {
						trans();
					}}, 0, delay);

					
				Context context = getApplicationContext();
				CharSequence text = "Transmitting Data...";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
			public void trans(){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						fab_cancel.setProgress((100*mind / bin.length), true);

					}
				});
				if (mind == bin.length || cancel == true){
					
					if (hasflash == true){
						if (isLighOn && camera != null){
							p.setFlashMode(Parameters.FLASH_MODE_OFF);
							camera.setParameters(p);
							camera.stopPreview();
							isLighOn = false;
						}
						if (camera != null && cancel == false){
							camera.release();

						}
					}
					else{
						runOnUiThread(new Runnable() {
							@Override
							public void run() {

								LinearLayout temp = (LinearLayout)findViewById(R.id.screen_trans);
								temp.setVisibility(View.GONE);

							}
						});
						//						screen.setVisibility(View.GONE);
					}
//					SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
//					
//					editor.putString("pcode_backup","");
//					editor.putString("saved_num","");
//					editor.commit();
					start = false;
					cancel = false;
					System.exit(0);
				}
				//				for (int i=0;i<bin.length;i++) {
				//					Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

				//					long current = System.nanoTime();

				if (((String)(bin[mind] + "")).equals("1")){
					Thread.currentThread().setPriority(Thread.MAX_PRIORITY);


					//						while((System.nanoTime() - current ) - delay*1000*1000 < 5){
					//							Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
					if (hasflash == true && camera != null){
						p.setFlashMode(Parameters.FLASH_MODE_TORCH);
						camera.setParameters(p);
						camera.startPreview();
						isLighOn = true;
						
					}
					else{
						//						screen.setVisibility(View.VISIBLE);
						runOnUiThread(new Runnable() {
							@Override
							public void run() {

								LinearLayout temp = (LinearLayout)findViewById(R.id.screen_trans);
								temp.setVisibility(View.VISIBLE);
								temp.setBackgroundColor(Color.WHITE);

							}
						});

					}
					//						}
					//						p.setFlashMode(Parameters.FLASH_MODE_OFF);
					//						camera.setParameters(p);
					//						camera.stopPreview();
					//						isLighOn = false;
				}


				else if (((String)(bin[mind] + "")).equals("0")){
					//						Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

					//						if (isLighOn == false) {
					//
					//							Log.i("info", "torch is on!");
					//
					//							p.setFlashMode(Parameters.FLASH_MODE_TORCH);
					//							camera.setParameters(p);
					//							camera.startPreview();
					//							isLighOn = true;
					//
					//						}
					//						while((System.nanoTime() - current )  - delay*1000*1000 < 5  ){
					//							Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
					if (hasflash == true && camera != null){
						p.setFlashMode(Parameters.FLASH_MODE_OFF);
						camera.setParameters(p);
						camera.stopPreview();
						isLighOn = false;
					}
					else{
						//						screen.setVisibility(View.VISIBLE);
						//						screen.setBackgroundColor(Color.BLACK);
						runOnUiThread(new Runnable() {
							@Override
							public void run() {

								LinearLayout temp = (LinearLayout)findViewById(R.id.screen_trans);
								temp.setVisibility(View.VISIBLE);
								temp.setBackgroundColor(Color.BLACK);

							}
						});

					}
					//						}

					//					s	isLighOn = true;
				}
				mind++;
				//					try {


				//						Thread.sleep(delay);
				//						try {
				//							TimeUnit.MILLISECONDS.sleep(delay);
				//						} catch (InterruptedException e) {
				//							// TODO Auto-generated catch block
				//							e.printStackTrace();
				//						}
				//gets the current time in milliseconds  


				//						try {
				//							synchronized (Thread.currentThread()) {
				//
				//								Thread.currentThread().wait(delay);	
				//							}
				//						} catch (InterruptedException e) {
				//							// TODO Auto-generated catch block
				//							e.printStackTrace();
				//						}
				//					} 

				//					long start = new Date().getTime();
				//					while(new Date().getTime() - start < delay){}
				//					} catch (InterruptedException e) {
				//						e.printStackTrace();
				//					}
				//				}

			}
		});

	}




	View.OnClickListener pinButtonHandler = new View.OnClickListener() {
		public void onClick(View v) {

			switch(v.getId()) {
			case R.id.button0:
				pcode += "0";
				fld = "pin" + (pcode.length() - 1) + "";
				Context context = getApplicationContext();
				CharSequence text = fld;

				break;
			case R.id.button1:
				pcode += "1";
				break;
			case R.id.button2:
				pcode += "2";
				break;
			case R.id.button3:
				pcode += "3";
				break;
			case R.id.button4:
				pcode += "4";
				break;
			case R.id.button5:
				pcode += "5";
				break;
			case R.id.button6:
				pcode += "6";
				break;
			case R.id.button7:
				pcode += "7";
				break;
			case R.id.button8:
				pcode += "8";
				break;
			case R.id.button9:
				pcode += "9";
				break;
			case R.id.buttonb:
				if (pcode.length() > 0){
					pcode = pcode.substring(0, pcode.length()-1);
				}
				else if (pcode.length() == 0){
					pcode = "";
				}
				break;


			}
			if (pcode.length() == 4){
				button0.setClickable(false);
				button0.setTextColor(Color.GRAY);
				button1.setClickable(false);
				button1.setTextColor(Color.GRAY);
				button2.setClickable(false);

				button2.setTextColor(Color.GRAY);
				button3.setClickable(false);

				button3.setTextColor(Color.GRAY);
				button4.setClickable(false);

				button4.setTextColor(Color.GRAY);
				button5.setClickable(false);

				button5.setTextColor(Color.GRAY);
				button6.setClickable(false);

				button6.setTextColor(Color.GRAY);
				button7.setClickable(false);

				button7.setTextColor(Color.GRAY);
				button8.setClickable(false);

				button8.setTextColor(Color.GRAY);
				button9.setClickable(false);

				button9.setTextColor(Color.GRAY);
				Drawable img = getApplicationContext().getResources().getDrawable( R.drawable.solution);
				img.setBounds( 0, 0, 98, 98 );
				buttont.setText("Next");
				buttont.setCompoundDrawables( img, null, null, null );
				// add here for buttont to be clickable  = true and some nyc color
				//
			}
			else{

				button0.setTextColor(Color.WHITE);
				button0.setClickable(true);

				button1.setTextColor(Color.WHITE);
				button1.setClickable(true);

				button2.setTextColor(Color.WHITE);
				button2.setClickable(true);

				button3.setTextColor(Color.WHITE);
				button3.setClickable(true);

				button4.setTextColor(Color.WHITE);
				button4.setClickable(true);

				button5.setTextColor(Color.WHITE);
				button5.setClickable(true);

				button6.setTextColor(Color.WHITE);
				button6.setClickable(true);

				button7.setTextColor(Color.WHITE);
				button7.setClickable(true);

				button8.setTextColor(Color.WHITE);
				button8.setClickable(true);

				button9.setTextColor(Color.WHITE);
				button9.setClickable(true);
				
				Drawable img = getApplicationContext().getResources().getDrawable( R.drawable.disable);
				buttont.setText("");
				img.setBounds( 0, 0, 98, 98 );
				buttont.setCompoundDrawables( img, null, null, null );
				//add here for buttont to be not clickable and some color u may like
				
			}

			if (pcode.length() == 0){
				pin0 = (ImageView)findViewById(R.id.pin0);
				pin0.setImageResource(R.drawable.dollar_empty2);
				pin1 = (ImageView)findViewById(R.id.pin1);
				pin1.setImageResource(R.drawable.dollar_empty2);
				pin2 = (ImageView)findViewById(R.id.pin2);
				pin2.setImageResource(R.drawable.dollar_empty2);
				pin3 = (ImageView)findViewById(R.id.pin3);
				pin3.setImageResource(R.drawable.dollar_empty2);
			}
			else if (pcode.length() > 0){
				fld = "pin" + (pcode.length() - 1) + "";

				pin0 = (ImageView)findViewById(R.id.pin0);

				pin1 = (ImageView)findViewById(R.id.pin1);

				pin2 = (ImageView)findViewById(R.id.pin2);

				pin3 = (ImageView)findViewById(R.id.pin3);
				switch (fld) {
				case "pin0":
					pin0.setImageResource(R.drawable.dollarf);

					pin1.setImageResource(R.drawable.dollar_empty2);

					pin2.setImageResource(R.drawable.dollar_empty2);

					pin3.setImageResource(R.drawable.dollar_empty2);
					break;

				case "pin1":

					pin1.setImageResource(R.drawable.dollarf);

					pin2.setImageResource(R.drawable.dollar_empty2);

					pin3.setImageResource(R.drawable.dollar_empty2);
					break;

				case "pin2":

					pin2.setImageResource(R.drawable.dollarf);

					pin3.setImageResource(R.drawable.dollar_empty2);

					break;

				case "pin3":

					pin3.setImageResource(R.drawable.dollarf);
					break;
				}
			}
		}
	};

	public static TimerTask pause(){
		return null;

	}
	public static String repeat(String str, int times) {
		return new String(new char[times]).replace("\0", str);
	}

	@Override
	public void onDestroy(){
		
		if (camera != null){
			if (hasflash == true){
				if (isLighOn && camera != null){
					Parameters p = camera.getParameters();
					p.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(p);
					camera.stopPreview();
					isLighOn = false;
				}
			}
			else{
				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						LinearLayout temp = (LinearLayout)findViewById(R.id.screen_trans);
						temp.setVisibility(View.GONE);

					}
				});
				//						screen.setVisibility(View.GONE);
			}
			
			camera.release();
		}
		super.onDestroy();

	}
	
	
	@Override
	public void onPause(){

		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.putBoolean("Login_session", false);
		editor.commit();
		super.onPause();

	}
	@Override
	public void onResume(){
		
			
		SharedPreferences prefs = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE); 
		if (!prefs.getBoolean("Login_session", false)){

			Intent i = new Intent(getApplicationContext(),com.mflt.icici.
					LoginActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			finish();
			startActivity(i);
		}
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.putBoolean("Login_session", true);
		editor.commit();
		super.onResume();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.light_sensor, menu);
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
