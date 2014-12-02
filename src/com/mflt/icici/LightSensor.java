package com.mflt.icici;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

public class LightSensor extends Activity {


	private boolean isLighOn = false;

	private Camera camera;

	private Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light_sensor);

		button = (Button) findViewById(R.id.toggle);
		Context context = this;
		PackageManager pm = context.getPackageManager();

		// if device support camera?
		if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Log.e("err", "Device has no camera!");
			return;
		}


		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				camera = Camera.open();
				final Parameters p = camera.getParameters();
				EditText field = (EditText)findViewById(R.id.lednum);
				String num = new String(field.getText().toString());
				String[] bin = new String[4*num.length()];
				int j = 0;
				String tempstr = new String();
				for(int i = 0; i < num.length();i++){
					
						Integer temp = new Integer(Integer.parseInt(num.charAt(i) + ""));
						
						tempstr = repeat("0",4-Integer.toBinaryString(temp).length()) + Integer.toBinaryString(temp);
						
						for(int index = 0; index < 4; index++){
						bin[j++] = tempstr.charAt(index) + "";
						
						}
						
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
					try {
						EditText delfield = (EditText)findViewById(R.id.leddelay);
						Integer delay = new Integer(Integer.parseInt(delfield.getText().toString()));
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
