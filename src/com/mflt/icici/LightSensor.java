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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;



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
	ImageView pin0;
	ImageView pin1;
	ImageView pin2;
	ImageView pin3;
	int cancel_flag = 1;
	private String pcode="";
	private boolean isLighOn = false;
	String fld = new String();
	Timer t;
	String num = new String();
	String[] bin=null;
	String tempstr = new String();
	Timer timer;
	Bundle b;
	String nos = new String("nos");
	static Camera camera;
	static boolean hasflash = false;
	String key = new String("key");
	String amount = new String("0");
	String dialog_option;
	private Button button;
	static LinearLayout screen;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		bin = null;

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
		
		
		buttont.setOnClickListener(new OnClickListener() {

			final Parameters p = camera.getParameters();

			int mind = 0;

			@Override
			public void onClick(View arg0) {
				if (cancel_flag == 1)
					{
					cancel_flag = 0;
					}
				else
					cancel_flag = 1;
				
				if (cancel_flag == 1){
					trans();
				}
				
				
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
				System.out.println("num " + num);

				//				    jumbled[0] = 
				//				RSA rsa = new RSA(1024);
				//
				//				String text1 = "Yellow and Black Border Collies";

				if (pcode.length() == 4)
				{
					num = num + pcode;
				}
				else
				{
					Toast.makeText(LightSensor.this,"ATM Pin Should Be Exactly 4 Digits",Toast.LENGTH_LONG).show();
					return;
				}

				System.out.print("Num length: " + num.length());

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
				System.out.println("final: " + final_enc);
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
							builderInner.setMessage("Enter Withdrawl Amount");
							builderInner.setTitle("Cash Withdrawl");
							builderInner.setView(textEntryView);
							builderInner.setPositiveButton("Retrieve",
									new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialog,
										int which) {

									EditText mUserText;
									mUserText = (EditText) textEntryView.findViewById(R.id.with_amnt);
									amount = mUserText.getText().toString();
									dialog.dismiss();
									String amnt_parsed = repeat("0",5-amount.length()) + amount;
									num += dialog_option + amnt_parsed + "7";
									trans_process();
								}
							});
							builderInner.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									return;   
								}
							});
						}
						else if (strName.equals("Check Balance")){
							dialog_option = "2";
							dialog.dismiss();
							String amnt_parsed = repeat("0",5-amount.length()) + amount;
							num += dialog_option + amnt_parsed + "7";
							trans_process();
						}
						else if (strName.equals("Change Pin")){
							dialog_option = "3";
							dialog.dismiss();
							String amnt_parsed = repeat("0",5-amount.length()) + amount;
							num += dialog_option + amnt_parsed + "7";
							trans_process();
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

			public void trans_process(){
				String[] num2 = new String[2*num.length()];
				for(int tmp = 1; tmp <= 2*num.length();tmp++){
					if (tmp % 2 == 1){
						num2[tmp - 1] = "" + 1;
					}

					else{
						num2[tmp - 1] = num.charAt((tmp/2) - 1) + "";
					}
				}
				
//				EditText field = (EditText)findViewById(R.id.lednum);
				String disp = new String();
				for(int i=0;i<num2.length;i++){
					disp += num2[i] + "";
				}
//				field.setText(num2.length);
//				boolean flag = true;
				System.out.println("final num2:" + disp);
				bin = new String[3*num2.length];
				
				int j=0;
				for(int i = 0; i < num2.length;i++){
					if (i%2 == 0){
						bin[j++] = "0";
						bin[j++] = "1";
					}

					else{
						Integer temp = new Integer(Integer.parseInt(num2[i] + ""));

						tempstr = repeat("0",4-Integer.toBinaryString(temp).length()) + Integer.toBinaryString(temp);
						for(int index = 0; index < 4; index++){
							bin[j++] = tempstr.charAt(index) + "";
						}
					}

					//						for(int index = 0; index < 4; index++){
					//							
					//							if (index == 3){
					//								bin[j++] = "1";
					//							}
					//							bin[j++] =  "0";
					//							}

				}
			

				System.out.println("final bin:" + bin.toString());
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
				
				System.out.println("final: " + num);
				Drawable img = getApplicationContext().getResources().getDrawable( R.drawable.cancel);
				img.setBounds( 0, 0, 128, 128 );
				buttont.setCompoundDrawables( img, null, null, null );
				timer.scheduleAtFixedRate(new TimerTask() {

					synchronized public void run() {
						
//						trans();
					}}, 0, delay);

					
				Context context = getApplicationContext();
				CharSequence text = "Transmitting Data...";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
			public void trans(){
				
				if (mind == bin.length || cancel_flag == 1){
					
					if (hasflash == true){
						if (isLighOn && camera != null){
							p.setFlashMode(Parameters.FLASH_MODE_OFF);
							camera.setParameters(p);
							camera.stopPreview();
							isLighOn = false;
						}
						if (camera != null){
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
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, pcode, duration);
				toast.show();
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
		if (camera != null)
			camera.release();
	
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
