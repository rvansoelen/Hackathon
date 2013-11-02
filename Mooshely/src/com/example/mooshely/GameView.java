package com.example.mooshely;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {

	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;
	private Sprite mooshely;
	private Static_Sprite cloud;
	private List<Background> backtiles = new ArrayList<Background>();
	private List<Foreground> foretiles = new ArrayList<Foreground>(); 
	//public List<String> map = new ArrayList<String>();
	public char[] Buffer = { 'R', 'y', 'a', 'n', '!' };
	private String line;
	public int[][] map;
	public int TilesX;
	public int TilesY;
	private Bitmap bmp;
	public int camX;
	public int camY;
	public int num_tiles;
	public int tiles_x;
	public int tiles_y;
	private Context contxt;

	public GameView(Context context) {
		super(context);
		contxt = context;
		gameLoopThread = new GameLoopThread(this);
		holder = getHolder();
		holder.addCallback(new SurfaceHolder.Callback() {
 
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				createSprites();
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
	}

	public void createSprites() {
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.mooshely);
		mooshely = new Mooshely(this, bmp);
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cloud);
		cloud = new Static_Sprite(this, bmp);
		bmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.could_tile);
		load_background();
		load_map("test_tileset.tmx");
	}

	public void load_map(String text_file) {
		// Store text file in /assets
		AssetManager am = contxt.getAssets();

		try {

			InputStream fid = am.open(text_file);
			Scanner sid = new Scanner(fid);

			sid.useDelimiter("\"");
			sid.nextLine();
			sid.nextLine();
			sid.findInLine("width=");
			TilesX = sid.nextInt();
			sid.findInLine("height=");
			TilesY = sid.nextInt();

			sid.useDelimiter(",|\n");

			while (!((line = sid.nextLine())
					.equals("  <data encoding=\"csv\">"))) {

				if (!sid.hasNextLine()) {

					break;
				}
			}

			map = new int[TilesX][TilesY];
			
			for (int y = 0; y < TilesY; y++) {
				for (int x = 0; x < TilesX; x++) {

					map[x][y] = sid.nextInt();
					//System.out.print(map[x][y]);
					
					create_map(map[x][y],x,y);
				}
				sid.next();
				System.out.print("\n");
			}

			sid.close();
			fid.close();

		} catch (Exception e) {
			System.out.print("gyufuyftyddrtdrtdrdrd");
			e.printStackTrace();
		}
		
		

	}

	private void create_map(int map, int x, int y) {
		switch (map) {
		//ground
        case 1: 
        	bmp = BitmapFactory.decodeResource(getResources(), R.drawable.earth_tile);
        	foretiles.add(new Foreground(this, bmp, x, y,"earth"));
        	break;
        case 2:
        	bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ocean_tile);
        	foretiles.add(new Foreground(this, bmp, x, y,"ocean"));
        	break;
        case 3:
        	bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bird_tile);
        	foretiles.add(new Foreground(this, bmp, x, y,"bird"));
        	break;
        	
		}
	}

	public void load_background() {
		tiles_x = this.getWidth() / bmp.getWidth() + 2;
		tiles_y = this.getHeight() / bmp.getHeight() + 2;
		num_tiles = tiles_x * tiles_y;
		// System.out.println(tiles_x);
		// System.out.println(tiles_y);
		for (int j = 0; j < tiles_x; j++) {
			for (int k = 0; k < tiles_y; k++) {
				// System.out.println(k);
				backtiles.add(new Background(this, bmp, j, k));
			}
		}
	}

	public void CameraUpdate() {
		camX = mooshely.x - this.getWidth() / 2;
		camY = mooshely.y - this.getHeight() / 2;
	}

	/*
	 * private Sprite createSprite(int resouce) { bmp =
	 * BitmapFactory.decodeResource(getResources(), resouce); return new
	 * Sprite(this, bmp); }     
	 */

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(0xffffffff);
		for (Background backtile : backtiles) {
			backtile.onDraw(canvas);
		}
		for (Foreground foretile : foretiles) {
			foretile.onDraw(canvas);
		}
		cloud.onDraw(canvas);
		mooshely.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// System.out.println("Touched");
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			System.out.println("DOWN");

			if (0 <= event.getX() && event.getX() < this.getWidth() / 2) {
				// Attack Gestures
			}
			if (this.getWidth() / 2 <= event.getX()
					&& event.getX() < this.getWidth()) {
				Sprite.touchX = event.getX();
				Sprite.touchY = event.getY();
			}

			break;
		}
		case MotionEvent.ACTION_UP: {
			System.out.println("UP");
			mooshely.SetXspeed(0);
			break;
		}
		case MotionEvent.ACTION_MOVE: {
			// angle=Math.atan2(Sprite.touchY-event.getY(),event.getX()-Sprite.touchX);

			if (event.getX() > Sprite.touchX) {
				System.out.println("RIGHT");
				mooshely.SetXspeed(10);
			}
			if (event.getX() < Sprite.touchX) {
				System.out.println("LEFT");
				/* if(Static_Sprite.Lwall()) { */
				mooshely.SetXspeed(-10);

			}
			if (event.getY() < Sprite.touchY - 10) {
				System.out.println("JUMP");
				if (mooshely.Bwall()) {
					mooshely.SetYspeed(-30);
				}
			}
		}

		}

		return true;
	}

}
