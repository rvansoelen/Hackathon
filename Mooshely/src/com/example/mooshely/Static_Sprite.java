package com.example.mooshely;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Static_Sprite extends Sprite{




	public Static_Sprite(GameView gameView, Bitmap bmp) {
		super(gameView, bmp);
		bmp = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.background);
		

	}


}

	