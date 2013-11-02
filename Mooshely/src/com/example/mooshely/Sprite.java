package com.example.mooshely;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
//A drawable Image that has a x y position and can be updated to change the drawn image or the image position
public class Sprite {
		// direction = 0 up, 1 left, 2 down, 3 right,
    	// animation = 3 back, 1 left, 0 front, 2 right
		int[] DIRECTION_TO_ANIMATION_MAP = {1};
		private static final int BMP_COLUMNS = 1;
		private static final int BMP_ROWS = 1;
		private static final int MAX_SPEED = 5;
		public int x = 0;
		public int y = 0;
		public int xSpeed=0;
		public int ySpeed=0;
		public int xAccel;
		public int yAccel;
		public GameView gameView;
		public Bitmap bmp;
		private int currentFrame = 0;
		public int width;
		public int height;
		boolean Lwall;
		boolean Rwall;
		public static float touchX;
		public static float touchY;
		boolean Twall;
		public int gForce=2;
		public static int CamX;
		public static int CamY;
		
		
		public Sprite(GameView gameView, Bitmap bmp) {
			this.gameView = gameView;
			this.bmp = bmp;
			this.width = bmp.getWidth() / BMP_COLUMNS;
			this.height = bmp.getHeight() / BMP_ROWS; 
			
			//x = gameView.getWidth()/2;
            //y = gameView.getHeight()-bmp.getHeight();

		}
		
		public void update () {
			
			this.extraUpdate();
			
			xSpeed=xSpeed +xAccel;
			ySpeed=ySpeed + yAccel;
			
			x = x + xSpeed;
			y = y + ySpeed;
			
			
			gameView.CameraUpdate();
			
		}
		
		public void extraUpdate() {
			
		}
		//Temporary until Map has collision
		public boolean Bwall(){
			if(y + height +ySpeed >=gameView.getHeight()) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public void onDraw(Canvas canvas) {
			update();
			Rect src = new Rect(0, 0, width, height);
			Rect dst = new Rect(x-gameView.camX, y-gameView.camY, x-gameView.camX+ width, y-gameView.camY + height);
			canvas.drawBitmap(bmp, src, dst, null);
		}
			
		private int getAnimationRow() {
			double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
			int direction = (int) Math.round(dirDouble) % BMP_ROWS;
			return 1; //DIRECTION_TO_ANIMATION_MAP[direction];
		}

		public void SetXspeed(int speed) {
			xSpeed=speed;
		}
		public void SetYspeed(int speed) {
			ySpeed=speed;
		}
}
