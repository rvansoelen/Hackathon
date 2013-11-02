package com.example.mooshely;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Mooshely extends Character{
	
	
	public Mooshely(GameView gameView, Bitmap bmp) {
		super(gameView, bmp);
		bmp = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.mooshely);
		
	}
	@Override
	public void extraUpdate () {
		if (!Bwall()){
		yAccel=gForce; 
	}
	else {
		yAccel=0;
		ySpeed=0;
	}
	
		
	}

}
