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
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

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
	private Camera camera;

	private Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
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

			@Override
			public void onClick(View arg0) {
				camera = Camera.open();
				final Parameters p = camera.getParameters();
				EditText field = (EditText)findViewById(R.id.lednum);
				String numint = new String(field.getText().toString());
				RSA rsa = new RSA(1024);
				//
//				String text1 = "Yellow and Black Border Collies";
				if (pcode.length() == 4)
				{
					numint = numint + pcode;
				}
				else{
				Toast toaste = Toast.makeText(getApplicationContext(), "Please Enter 4 Digit Number", Toast.LENGTH_LONG);	
				toaste.show();
				if (camera != null){
					camera.release();
				}
				return;
				}
				System.out.print("Plaintext: " + numint);
				BigInteger plaintext = new BigInteger(numint.getBytes());
				//
				
				BigInteger ciphertext = rsa.encrypt(plaintext);
				String num = "" + ciphertext;
				System.out.print("Ciphertext: " + ciphertext);
				Toast toastte = Toast.makeText(getApplicationContext(), num, Toast.LENGTH_LONG);	
				toastte.show();
				
				plaintext = rsa.decrypt(ciphertext);
				//
				String text2 = new String(plaintext.toByteArray());
				System.out.print("Plaintext: " + text2);

				String[] bin = new String[4*num.length()];
				//				+ num.length()];
				int j = 0;
				String tempstr = new String();
				//				bin[0]="0";
				//				bin[1] ="0";
				//				bin[2] = "0";
				//				bin[3] ="1";
				for(int i = 0; i < num.length();i++){

					Integer temp = new Integer(Integer.parseInt(num.charAt(i) + ""));

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

				Toast toasty = Toast.makeText(getApplicationContext(), disp, Toast.LENGTH_SHORT);
				toasty.show();


				for (int i=0;i<bin.length;i++) {

					if (((String)(bin[i] + "")).equals("1")){
						//						if (isLighOn) {
						//
						//							Log.i("info", "torch is turn off!");
						//
						//							p.setFlashMode(Parameters.FLASH_MODE_OFF);
						//							camera.setParameters(p);
						//							camera.stopPreview();
						//							isLighOn = false;
						//
						//						}

						p.setFlashMode(Parameters.FLASH_MODE_TORCH);
						camera.setParameters(p);
						camera.startPreview();
						isLighOn = true;


					}

					else if (((String)(bin[i] + "")).equals("0")){
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
						p.setFlashMode(Parameters.FLASH_MODE_OFF);
						camera.setParameters(p);
						camera.stopPreview();
						isLighOn = false;

					}
					//					try {
					EditText delfield = (EditText)findViewById(R.id.leddelay);
					Integer delay = new Integer(Integer.parseInt(delfield.getText().toString()));


					//						Thread.sleep(delay);
					//						try {
					//							TimeUnit.MILLISECONDS.sleep(delay);
					//						} catch (InterruptedException e) {
					//							// TODO Auto-generated catch block
					//							e.printStackTrace();
					//						}
					//gets the current time in milliseconds  

					long current = System.nanoTime();
					while((System.nanoTime() - current ) - delay*1000*1000 > 10){
					} 

					//					long start = new Date().getTime();
					//					while(new Date().getTime() - start < delay){}
					//					} catch (InterruptedException e) {
					//						e.printStackTrace();
					//					}
				}
				if (isLighOn){
					p.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(p);
					camera.stopPreview();
					isLighOn = false;
				}
				Context context = getApplicationContext();
				CharSequence text = "Code Successfully Transmitted";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				if (camera != null){
					camera.release();
				}
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
