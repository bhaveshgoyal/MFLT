package com.mflt.icici;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CardsActivity extends Activity {
	ListView list;
	Button floatbut;
	String[] web = {
			"ICICI",
			"HDFC",
			"IDBI",
			"CANARA",
			"BOB",
			"BOI"
	} ;
	Integer[] imageId = {
			R.drawable.icici,
			R.drawable.hdfc,
			R.drawable.idbi,
			R.drawable.canara,
			R.drawable.bob,
			R.drawable.boi,
	};
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
		CardsAdapter adapter = new	
				CardsAdapter(CardsActivity.this, web, imageId);
		list=(ListView)findViewById(R.id.cardlist);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(CardsActivity.this, "You Selected " +web[+position], Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(),com.mflt.icici.
						LightSensor.class);
				startActivity(i);
			}
		});


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
