package com.example.mooshely;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background extends Sprite{
	
	public int num_tiles;
	public int ID_x;
	public int ID_y;
	public int x_len;
	public int y_len;
	
	
	
	public Background(GameView gameView, Bitmap bmp, int id_x, int id_y) {
		super(gameView, bmp);
		//num_tiles=(gameView.getWidth()/bmp.getWidth()+2)*(gameView.getHeight()/bmp.getHeight()+2);
		ID_x=id_x;
		ID_y=id_y;
		x=gameView.camX+bmp.getWidth()*ID_x;
		y=gameView.camY+bmp.getHeight()*ID_y;
		x_len=bmp.getWidth()*gameView.tiles_x;
		y_len=bmp.getHeight()*gameView.tiles_y;
	}
	
	

	@Override
	public void update() {
		if (x>gameView.camX+gameView.getWidth()) {
			x=x-x_len;
		}
		if (x+bmp.getWidth()<gameView.camX) {
			x=x+x_len;
		}
		if (y>gameView.camY+gameView.getHeight()) {
			y=y-y_len;
		}
		if (y+bmp.getHeight()<gameView.camY) {
			y=y+y_len;
		}
	}
}
