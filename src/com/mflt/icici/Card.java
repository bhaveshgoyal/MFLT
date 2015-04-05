package com.mflt.icici;

public class Card {
	
	public String name;
	public String number;
	public int clicks=0;
	public int image;
	
	
	public Card(String card_name, String card_number, int draw){
		this.name = card_name;
		this.number = card_number;
		this.image = draw;
	}
	
	
	public void setname(String sname){
		this.name  = sname;
	}
	
	public void setimg(int imgid){
		this.image = imgid;
	}
	
	public void setnum(String num){
		this.number = num;
	}
	
	public String getname(){
		return this.name;
	}
	
	public int getclicks(){
		return this.clicks;
	}
	
	public int getimg(){
		return this.image;
	}
	
	public String getnum(){
		return this.number;
	}
}