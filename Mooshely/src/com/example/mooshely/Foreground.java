package com.example.mooshely;

import android.graphics.Bitmap;

public class Foreground extends Sprite{

	public int num_tiles;
	public int ID_x;
	public int ID_y;
	public int x_len;
	public int y_len;
	public String Type;
	
	public Foreground(GameView gameView, Bitmap bmp, int id_x, int id_y, String type) {
		super(gameView, bmp);
		//num_tiles=(gameView.getWidth()/bmp.getWidth()+2)*(gameView.getHeight()/bmp.getHeight()+2);
		Type=type;
		ID_x=id_x;
		ID_y=id_y;
		x=gameView.camX+bmp.getWidth()*ID_x;
		y=gameView.camY+bmp.getHeight()*ID_y;
		x_len=bmp.getWidth()*gameView.tiles_x;
		y_len=bmp.getHeight()*gameView.tiles_y;
	}

}
