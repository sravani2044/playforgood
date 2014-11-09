package com.playforgood.ui;

import android.graphics.Bitmap;

/**
 * 
 * @author manish.s
 *
 */
public class Item {
	Bitmap image;
	String title;
	String packageName;
	public static final Item DEFAULT_ITEM = new Item(null, "DEFAULT_TITLE", "com.example.default"); 
	
	public Item(Bitmap image, String title, String packageName) {
		super();
		this.image = image;
		this.title = title;
		this.packageName = packageName;
	}
	public Bitmap getImage() {
		return image;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}


