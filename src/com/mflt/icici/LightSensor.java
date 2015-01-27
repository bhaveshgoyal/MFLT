package com.mflt.icici;

import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionProvider.VisibilityListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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
	private Button button;
	static LinearLayout screen;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		bin = null;
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
				String[] array=b.getStringArray(nos);
				int pos = b.getInt(key);
				mind = 0;
				EditText field = (EditText)findViewById(R.id.lednum);
//				num = "" + 3;
				num = "";
//				num += array[pos];
				num += field.getText().toString();
				 if (num.length() != 16){
						Toast toaste = Toast.makeText(getApplicationContext(), "Please Enter 16 Digit Card Number", Toast.LENGTH_LONG);	
						toaste.show();
//						if (camera != null){
//							camera.release();
//						}
						return;
//						}	
								    }
				  BigInteger p = new BigInteger("227");
				    BigInteger q = new BigInteger("109");
				    BigInteger n = new BigInteger("24743");
				    BigInteger e = new BigInteger("13");
//					System.out.println(n);  
				    RSA rsa = new RSA(n,e);
				    int[] order = {12,3,7,5,16,14,9,1,8,13,4,11,15,10,6,2};
				    String[] jumbled = new String[16];
				    for (int i = 0; i < jumbled.length; i++) {
						if (order[i] == 13 || order[i] == 15 || order[i] == 14 || order[i] == 16){
							  BigInteger plain = new BigInteger(num.charAt(order[i]-1) + "");
							     jumbled[i] = plain.modPow(e, n).toString();
						}
						else
				    	jumbled[i] = num.charAt(order[i]-1) + ""; 
					}
				    num = "";
				    for (int i = 0; i < jumbled.length; i++) {
						num += jumbled[i];
					}
				    System.out.println("num " + num);
				   
//				    jumbled[0] = 
				//				RSA rsa = new RSA(1024);
				//
				//				String text1 = "Yellow and Black Border Collies";
//								if (pcode.length() == 4)
//								{
////									num = num + pcode;
////									String temp = rsa.encrypt(num);
////									num = temp;
//								}
//								else{

//					
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
								String[] num2 = new String[2*num.length()];
								for(int tmp = 1; tmp <= 2*num.length();tmp++){
								if (tmp % 2 == 1){
									num2[tmp - 1] = "" + 1;
								}
								else{
									num2[tmp - 1] = num.charAt((tmp/2) - 1) + "";
								}
								}
				bin = new String[4*num2.length];
				//				+ num.length()];
				int j=0;
				for(int i = 0; i < num2.length;i++){

					Integer temp = new Integer(Integer.parseInt(num2[i] + ""));

					tempstr = repeat("0",4-Integer.toBinaryString(temp).length()) + Integer.toBinaryString(temp);
					for(int index = 0; index < 4; index++){
						bin[j++] = tempstr.charAt(index) + "";

					}
					//						for(int index = 0; index < 4; index++){
					//							
					//							if (index == 3){
					//								bin[j++] = "1";
					//							}
					//							bin[j++] =  "0";
					//							}

				}
				String disp = new String();
				for(int i=0;i<bin.length;i++){
					disp += bin[i] + "";
				}

//				Toast toasty = Toast.makeText(getApplicationContext(), disp, Toast.LENGTH_LONG);
//				toasty.show();

				timer = new Timer();
				EditText delfield = (EditText)findViewById(R.id.leddelay);
				Integer delay = new Integer(Integer.parseInt(delfield.getText().toString()));
				if (delay.toString() == ""){
					Toast toaste = Toast.makeText(getApplicationContext(), "Please Enter Frequency for Transmission", Toast.LENGTH_LONG);	
					toaste.show();
//					if (camera != null){
//						camera.release();
//					}
					return;
				}
					
				timer.scheduleAtFixedRate(new TimerTask() {

					synchronized public void run() {

						trans();


					}}, 0, delay);
				
				Context context = getApplicationContext();
				CharSequence text = "Transmitting Data...";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
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

			public void trans(){

				if (mind == bin.length){
					if (hasflash == true){
					if (isLighOn){
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
					if (hasflash == true){
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
					if (hasflash == true){
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
