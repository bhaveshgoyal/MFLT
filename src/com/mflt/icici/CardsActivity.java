package com.mflt.icici;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class CardsActivity extends Activity {
	ListView list;
	Button floatbut;
	Card[] cards = new Card[3];
	String[] web = new String[3] ;
	
	
	String nos = new String("nos");
	String key = new String("key");
	Integer[] imageId = new Integer[3];
	Timer t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_cards);
		t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				finish();
			}
		}, 1000*60*4);
		/*floatbut = (Button)findViewById(R.id.floating);
		floatbut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(CardsActivity.this);

				alert.setTitle("Add Card Data");
				alert.setMessage("Card Name");

				// Set an EditText view to get user input 
				final EditText input = new EditText(CardsActivity.this);
				alert.setView(input);

				alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				  String value = input.getText().toString();
				  // Do something with value!
				  }
				});

				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
				  }
				});

				alert.show();

			}
		});
		 */

		cards[0] = new Card("ICICI GOLD","4792721650687941", R.drawable.icici);
		cards[1] = new Card("ICICI PLATINUM","9856412365789654" , R.drawable.hdfc);
		cards[2] = new Card("ICICI RUBY","7896542312469873" , R.drawable.idbi);
		
		for (int i=0; i < cards.length; i++) {
			web[i] = cards[i].getnum();
			imageId[i] = cards[i].getimg();
		}
		
//		SharedPreferences prefs = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE); 
//		int sortclicks_i;
//		int sortclicks_j;
//		for (int i = 0; i < cards.length; i++) {
//			
//			sortclicks_i = prefs.getInt("clicks_card["+ i+"]", 0);
//			
//			for (int j = i+1; j < cards.length; j++) {
//				sortclicks_j = prefs.getInt("clicks_card["+ j+"]", 0);
//				if (sortclicks_j < sortclicks_i){
//					String temp = web[j];
//					web[j] = web[i];
//					web[i] = temp;
//					
//					int temp2 = imageId[j];
//					imageId[j] = imageId[i];
//					imageId[i] = temp2;
//				}
//				
//			}
//			
//		}
//		Integer[] imageId = {
//				R.drawable.icici,
//				R.drawable.hdfc,
//				R.drawable.idbi,
//				R.drawable.canara,
//				R.drawable.bob,
//				R.drawable.boi,
//		};
		
		CardsAdapter adapter = new	
				CardsAdapter(CardsActivity.this, web, imageId);
		list=(ListView)findViewById(R.id.cardlist);
	
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(CardsActivity.this, "You Selected " +web[+position], Toast.LENGTH_SHORT).show();
				SharedPreferences prefs = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE); 
				int getclicks = prefs.getInt("clicks_card["+ position+"]", 0);
				SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
				 editor.putInt("clicks_card["+ position+"]", getclicks + 1);
				 System.out.println(getclicks);
				 editor.commit();
				Bundle b=new Bundle();
				b.putStringArray(nos, web);
				b.putInt(key, +position);
				Intent i = new Intent(getApplicationContext(),com.mflt.icici.
						LightSensor.class);
				i.putExtras(b);

				startActivity(i);
			}
		});


	}
	
	@Override
	public void onResume(){
		SharedPreferences prefs = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE); 
		int sortclicks_i;
		int sortclicks_j;
		for (int i = 0; i < cards.length; i++) {
			
			sortclicks_i = prefs.getInt("clicks_card["+ i+"]", 0);
			
			for (int j = i+1; j < cards.length; j++) {
				sortclicks_j = prefs.getInt("clicks_card["+ j+"]", 0);
				if (sortclicks_j > sortclicks_i){
					String temp = web[j];
					web[j] = web[i];
					web[i] = temp;
					
					int temp2 = imageId[j];
					imageId[j] = imageId[i];
					imageId[i] = temp2;
					
					SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
					 editor.putInt("clicks_card["+ i+"]", sortclicks_j);
					 editor.putInt("clicks_card["+ j+"]", sortclicks_i);
					 editor.commit();
					
				}
				
			}
			
		}

		CardsAdapter adapter = new	
				CardsAdapter(CardsActivity.this, web, imageId);
		list=(ListView)findViewById(R.id.cardlist);
		
		list.setAdapter(adapter);
		super.onResume();	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cards, menu);
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
