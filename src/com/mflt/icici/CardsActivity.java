package com.mflt.icici;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class CardsActivity extends Activity {
	ListView list;
	Button floatbut;
	
	ArrayList<Card> cards_sel = new ArrayList<Card>();
	ArrayList<String> web_list = new ArrayList<String>();
	ArrayList<Integer> img_list = new ArrayList<Integer>();
	ArrayList<String> info_list = new ArrayList<String>();
	
	int[] Imgarr;
	String nos = new String("nos");
	String key = new String("key");
	Integer[] imageId = new Integer[10];
	Timer t;
	int idx;
	int size_ind;
	long rem_time;
	FloatingActionButton saved_float;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_cards);
		
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.putBoolean("Login_session", true);
		editor.commit();
		
		t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				finish();
			}
		}, 1000*60*2);
		
		
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
//		cards_sel.add(0,new Card("ICICI GOLD",getsharednum(0), R.drawable.icici));
//		putcardcnt();
//		cards_sel.add(1,new Card("ICICI PLATINUM",getsharednum(1) , R.drawable.hdfc));
//		putcardcnt();
//		cards_sel.add(2,new Card("ICICI RUBY",getsharednum(2) , R.drawable.idbi));
//		putcardcnt();
//		
//		
//		putsharednum(0, cards_sel.get(0).number);
//		putsharednum(1, cards_sel.get(1).number);
//		putsharednum(2, cards_sel.get(2).number);
//		
//		putsharedimg(0, cards_sel.get(0).image);
//		putsharedimg(1, cards_sel.get(1).image);
//		putsharedimg(2, cards_sel.get(2).image);
//		
		final SharedPreferences prefs = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);
		
		
		saved_float = (FloatingActionButton)findViewById(R.id.saved_floating);
		
		
		if (!prefs.getString("saved_num", "").equals("")){
			
			final long time_to_clean = prefs.getLong("rem_time", Long.parseLong("60000"));
			
			new CountDownTimer(time_to_clean, 1000) {
			
			SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
			
		     public void onTick(long millisUntilFinished) {
		    	 editor.putLong("rem_time",millisUntilFinished);
				 saved_float.setProgress((int) (100 * (millisUntilFinished) / 60000), true);
		    	 editor.commit();
				 SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);

				 System.out.println("tine: " + pref.getLong("rem_time", Long.parseLong("0")));

			 }

		     public void onFinish() {
		    	 saved_float.hide(true);
		    		editor.putString("saved_num", "");
					editor.putString("saved_amnt","");
				 	editor.remove("rem_time");
					editor.commit();
		     }
		  }.start();
		}
		Button floatb = (Button)findViewById(R.id.floating);
		if (prefs.getString("saved_num", "").equals("")){
			saved_float.setVisibility(View.GONE);
		}
		
		
		for (int i = 0; i < getcardcnt(); i++) {
			cards_sel.add(i,new Card("ICICI NEW",getsharednum(i), getsharedimg(i)));
		}
			
		for (int i=0; i < getcardcnt(); i++) {
			web_list.add(i,getsharednum(i));
			img_list.add(i,getsharedimg(i));
			info_list.add(i, getcardinfo(i));
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
		

		System.out.println("length: " + web_list.size());
		CardsAdapter adapter = new	
				CardsAdapter(CardsActivity.this, web_list, img_list,info_list);
		
		
		list=(ListView)findViewById(R.id.cardlist);
//	
		list.setAdapter(adapter);
		
		saved_float.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Bundle b = new Bundle();
				b.putBoolean("from_saved", true);

				Intent i = new Intent(getApplicationContext(), com.mflt.icici.
						LightSensor.class);
				i.putExtras(b);
				startActivity(i);


			}
		});
		
		floatb.setOnClickListener(new OnClickListener() {
			
			Integer[] Imgarr = {R.drawable.icici, R.drawable.platinum, R.drawable.coral};
			
			@Override
			public void onClick(View v) {
				size_ind = cards_sel.size();
				
				idx = new Random().nextInt(Imgarr.length);
				System.out.println("index :" + idx);
				cards_sel.add(size_ind,new Card("ICICI NEW",getsharednum(size_ind), Imgarr[idx]));	
				
				putsharednum(size_ind, cards_sel.get(size_ind).number);
				putsharedimg(size_ind, Imgarr[idx]);
				putcardinfo(size_ind, "No Info Added");
				putcardcnt();
				
				info_list.add(size_ind,getcardinfo(size_ind));
				web_list.add(size_ind,getsharednum(size_ind));
				img_list.add(size_ind,getsharedimg(size_ind));
				
				CardsAdapter adapter = new	
						CardsAdapter(CardsActivity.this, web_list, img_list, info_list);
				
				list=(ListView)findViewById(R.id.cardlist);
				list.setAdapter(adapter);
			/////////////////////////////////////////////////
			
				AlertDialog.Builder builderSingle = new AlertDialog.Builder(
						CardsActivity.this);
				builderSingle.setIcon(R.drawable.ic_launcher);
				builderSingle.setTitle("New Card Data");
				builderSingle.setMessage("Input Card Number To Be Used");
				LayoutInflater factory = LayoutInflater.from(CardsActivity.this);
				final View textEntryView = factory.inflate(R.layout.design_card, null);

				TextView card_n = (TextView) textEntryView.findViewById(R.id.edit_card);
				card_n.setVisibility(View.GONE);
				EditText mUserText;
				mUserText = (EditText) textEntryView.findViewById(R.id.card_info);
				mUserText.setVisibility(View.GONE);

				builderSingle.setView(textEntryView);
				builderSingle.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builderSingle.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						EditText mUserText;
						mUserText = (EditText) textEntryView.findViewById(R.id.card_num);
						mUserText.setVisibility(View.VISIBLE);
						String new_n = new String(mUserText.getText().toString());
						if (new_n.length() != 16 || (new_n.charAt(0) != '3' && new_n.charAt(0) != '4' && new_n.charAt(0) != '5'))
							Toast.makeText(CardsActivity.this,"Invalid Card Number",Toast.LENGTH_SHORT).show();
						else{
							int cap = 0;
							for (int i = 0; i < getcardcnt(); i++) {
								if (new_n.equals(getsharednum(i))){
									Toast.makeText(CardsActivity.this,"Card Number already Exists",Toast.LENGTH_SHORT).show();
									cap = 1;
								}
							}
							if (cap != 1){
								putsharednum(size_ind, new_n);
								Toast.makeText(CardsActivity.this,"Card Number Successfully Edited",Toast.LENGTH_SHORT).show();
								dialog.dismiss();
							}
						}

					}
				});
				builderSingle.show();				
			
			/////////////////////////////////////////////////
			}
		});
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(CardsActivity.this, "You Selected " +web[+position], Toast.LENGTH_SHORT).show();
				SharedPreferences prefs = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE); 
				 if (getsharednum(position).equals(""))
					 Toast.makeText(CardsActivity.this, "Card Number Not Saved", Toast.LENGTH_LONG).show();
				 else {				 
					 	int getclicks = prefs.getInt("clicks_card["+ position+"]", 0);
						SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
						 editor.putInt("clicks_card["+ position+"]", getclicks + 1);
						 System.out.println(getclicks);
						 editor.commit();
						
				Bundle b=new Bundle();
				b.putInt(key, +position);
				Intent i = new Intent(getApplicationContext(),com.mflt.icici.
						LightSensor.class);
				i.putExtras(b);

				startActivity(i);
			}
			}
		});
		
		list.setOnItemLongClickListener(new OnItemLongClickListener() { 
	        

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				final int pos = position;
				AlertDialog.Builder builderSingle = new AlertDialog.Builder(
						CardsActivity.this);
				builderSingle.setIcon(R.drawable.ic_launcher);
				builderSingle.setTitle("Card Options");
				final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
						CardsActivity.this,
						android.R.layout.select_dialog_singlechoice);
				arrayAdapter.add("View Card Number");
				arrayAdapter.add("Edit Card Number");
				arrayAdapter.add("Card Info");
				arrayAdapter.add("Delete Card");

				LayoutInflater factory = LayoutInflater.from(CardsActivity.this);
				final View textEntryView = factory.inflate(R.layout.design_card, null);  

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
								CardsActivity.this);
						if (strName.equals("Edit Card Number")){
							builderInner.setTitle("Edit Card Number");
							builderInner.setMessage("Enter New Card Number");
							TextView card_n = (TextView)textEntryView.findViewById(R.id.edit_card);
							card_n.setVisibility(View.GONE);
							EditText mUserText;
							mUserText = (EditText) textEntryView.findViewById(R.id.card_info);
							mUserText.setVisibility(View.GONE);
							
							builderInner.setView(textEntryView);
							builderInner.setPositiveButton("Done",
									new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialog,
										int which) {

									EditText mUserText;
									mUserText = (EditText) textEntryView.findViewById(R.id.card_num);
									mUserText.setVisibility(View.VISIBLE);
									String new_n = new String(mUserText.getText().toString());
									if (new_n.equals(""))
										dialog.dismiss();
									else if (new_n.length() != 16 || (new_n.charAt(0) != '3' && new_n.charAt(0) != '4' && new_n.charAt(0) != '5'))
										Toast.makeText(CardsActivity.this,"Invalid Card Number",Toast.LENGTH_SHORT).show();
									else{
									int cap = 0;
									for (int i = 0; i < getcardcnt(); i++) {
										if (new_n.equals(getsharednum(i))){
											Toast.makeText(CardsActivity.this,"Card Number already Exists",Toast.LENGTH_SHORT).show();
											cap = 1;
										}
									}
									if (cap != 1){
										putsharednum(pos, new_n);
									Toast.makeText(CardsActivity.this,"Card Number Successfully Edited",Toast.LENGTH_SHORT).show();
									dialog.dismiss();
									}
									}
									
								}
							});
							builderInner.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									return;   
								}
							});
						}
						else if (strName.equals("View Card Number")){
							builderInner.setTitle("Your Card Number is");
							if (getsharednum(pos).equals(""))
								builderInner.setMessage("No Card Number Saved");
							else{
								
								builderInner.setMessage(getstar(pos) );
							}
							builderInner.setPositiveButton("Done",
									new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialog,
										int which) {

									dialog.dismiss();
									
								}
							});

						}

						else if (strName.equals("Delete Card")){
							builderInner.setTitle("Confirm Delete");
							builderInner.setMessage("Are you Sure you want to Remove the Card ?");
							builderInner.setPositiveButton("Delete",
									new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialog,
										int which) {
									
									removesharednum(pos);
									removesharedimg(pos);
									removecardinfo(pos);
									removecardcnt();
									
									info_list.remove(pos);
									web_list.remove(pos);
									img_list.remove(pos);
									cards_sel.remove(pos);
									SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
									 editor.remove("clicks_card["+ pos+"]");
									 editor.commit();
									
									CardsAdapter adapter = new	
											CardsAdapter(CardsActivity.this, web_list, img_list, info_list);
									
									list=(ListView)findViewById(R.id.cardlist);
									list.setAdapter(adapter);
									Toast.makeText(CardsActivity.this, "Card Successfully Removed", Toast.LENGTH_SHORT).show();
									dialog.dismiss();
									
									
								}
							});

							builderInner.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									return;   
								}
							});

						}

						else if (strName.equals("Card Info")){
							
							builderInner.setTitle("Your Card Info");
							
							if (getcardinfo(pos).equals(""))
								builderInner.setMessage("No Information Saved");
							else
								builderInner.setMessage(getcardinfo(pos));
							
							TextView card_n = (TextView)textEntryView.findViewById(R.id.edit_card);
							card_n.setVisibility(View.GONE);
							
							EditText Text = (EditText) textEntryView.findViewById(R.id.card_num);
							Text.setVisibility(View.GONE);
							
							builderInner.setView(textEntryView);
							builderInner.setPositiveButton("Done",
									new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialog,
										int which) {

									EditText mUserText;
									mUserText = (EditText) textEntryView.findViewById(R.id.card_info);
									mUserText.setVisibility(View.VISIBLE);
									String new_n = new String(mUserText.getText().toString());
									if (!new_n.equals("")){
									putcardinfo(pos, new_n);
									Toast.makeText(CardsActivity.this,"Card Information Changed",Toast.LENGTH_SHORT).show();
									}
									
									for (int i=0; i < getcardcnt(); i++) {
										info_list.set(i, getcardinfo(i));
									}
									
									CardsAdapter adapter = new	
											CardsAdapter(CardsActivity.this, web_list, img_list,info_list);
									
									
									
									ListView list=(ListView)findViewById(R.id.cardlist);
									list.setAdapter(adapter);
									dialog.dismiss();
								}	
							});
							
							builderInner.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									return;   
								}
							});


						}
						builderInner.show();

					}
				});
				builderSingle.show();

				
				return true;
			}
	    });


	}
	
	public String getstar(int indx){
		String ret = new String();
		for (int i = 0; i < getsharednum(indx).length() - 3; i++) {
			ret += "*";
		}
		
		return (ret + getsharednum_last3(indx));
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
		sort();
		super.onResume();	
	}
	
	public void sort(){
		int sortclicks_i;
		int sortclicks_j;

		SharedPreferences prefs = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE); 
		System.out.println("0 : " + prefs.getInt("clicks_card[0]", 0));
		System.out.println("1:" + prefs.getInt("clicks_card[1]", 0));
		System.out.println("count: " + getcardcnt());
		for (int i = 0; i < getcardcnt(); i++) {
			sortclicks_i = prefs.getInt("clicks_card["+ i+"]", 0);
			
			for (int j = i+1; j < getcardcnt(); j++) {
				
				sortclicks_j = prefs.getInt("clicks_card["+ j+"]", 0);
				if (sortclicks_j > sortclicks_i){
					String temp = web_list.get(j);
					web_list.set(j, web_list.get(i));
					web_list.set(i, temp);
					
					Integer temp2 = img_list.get(j);
					img_list.set(j, img_list.get(i));
					img_list.set(i, temp2);
					

					String temp4 = info_list.get(j);
					info_list.set(j, info_list.get(i));
					info_list.set(i, temp4);
					
					Card temp3 = cards_sel.get(j);
					cards_sel.set(j, cards_sel.get(i));
					cards_sel.set(i, temp3);
					
					String tmpnum = getsharednum(j);
					putsharednum(j, getsharednum(i));
					putsharednum(i, tmpnum);

					Integer tmpimg = getsharedimg(j);
					putsharedimg(j, getsharedimg(i));
					putsharedimg(i, tmpimg);

					String tmpinfo = getcardinfo(j);
					putcardinfo(j, getcardinfo(i));
					putcardinfo(i, tmpinfo);
					
					SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
					 editor.putInt("clicks_card["+ i+"]", sortclicks_j);
					 editor.putInt("clicks_card["+ j+"]", sortclicks_i);
					 editor.commit();
					
				}
				
			}
			
		}

		CardsAdapter adapter = new	
				CardsAdapter(CardsActivity.this, web_list, img_list,info_list);
		list=(ListView)findViewById(R.id.cardlist);
		
		list.setAdapter(adapter);
	}
	public void onPause(){
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.putBoolean("Login_session", false);
		editor.commit();
		super.onPause();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cards, menu);
		return true;
	}
	
	public void putsharednum(int idx, String num){
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.putString("card_numb["+idx+"]", num);
		if (num.length() > 4)
		editor.putString("card_numb_last3["+idx+"]", num.substring(num.length() - 4, num.length()));
		editor.commit();
	}
	public void removesharednum(int idx){
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.remove("card_numb["+idx+"]");
		
		editor.remove("card_numb_last3["+idx+"]");
		editor.commit();
	}
	
	public String getsharednum(int idx){
		SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);
		
		return pref.getString("card_numb[" + idx + "]", ""); 
	}
	
	public String getsharednum_last3(int idx){
		SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);
		
		return pref.getString("card_numb_last3[" + idx + "]", ""); 
	}
	
	public void putsharedimg(int idx, int img){
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.putInt("card_img["+idx+"]", img);
		editor.commit();
	}
	public void removesharedimg(int idx){
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.remove("card_img["+idx+"]");
		editor.commit();
	}
	public void putcardcnt(){
		
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.putInt("card_count", getcardcnt() + 1);
		editor.commit();
	}
	public void removecardcnt(){
		
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.putInt("card_count", getcardcnt() - 1);
		editor.commit();
	}
	
	public int getcardcnt(){
		
		SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);
		return pref.getInt("card_count", 0); 
	}
	
	public void putcardinfo(int idx, String info){
		
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.putString("card_info[" + idx + "]", info);
		editor.commit();
	}
	public void removecardinfo(int idx){
		
		SharedPreferences.Editor editor = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE).edit();
		editor.remove("card_info[" + idx + "]");
		editor.commit();
	}
	public String getcardinfo(int idx){
		
		SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);
		return pref.getString("card_info[" + idx + "]", ""); 
	}
	
	public int getsharedimg(int idx){
		SharedPreferences pref = getSharedPreferences("ICICI_PREFS", MODE_PRIVATE);
		
		return pref.getInt("card_img[" + idx + "]", 0); 
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