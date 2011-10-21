/**
 * 
 */
package com.vujicic.aleksa.rpgfight;

import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.vujicic.aleksa.rpgfight.sprite.Screen;

//TODO: FINISH JAVA DOCS

/**
 * @author Aleksa Vujicic
 *
 */
public class RPGFightThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private RPGFightView view;
	private Context context;
	
	private long sleepTime;
	private long delay=70;

	private boolean running;
	private boolean finLoad = false;
	
	public long fps;
	
	private Screen mainScreen; 
	
	
	public RPGFightThread(SurfaceHolder surfaceHolder, Context context, RPGFightView view) {
		this.surfaceHolder = surfaceHolder;
		this.view = view;
		this.context = context;
		mainScreen = new Screen(context, R.drawable.testbg, view.getHeight(), view.getWidth());
		finLoad = true;
	}
	
	@Override
	public void run() {
		
		//UPDATE
		while (running) {
			
			if (!finLoad) continue;
			
			long beforeTime = System.nanoTime();
			mainScreen.update();
			
			Canvas c = null;
			try {
				c = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					mainScreen.draw(c, 0, 0);
					Paint p = new Paint();
					p.setColor(Color.BLACK);
					p.setTextSize(20);
					c.drawText("FPS: "+fps, 700, 478, p);
				}
			} finally {
				if (c != null) {
					surfaceHolder.unlockCanvasAndPost(c);
				}
			}
			
			this.sleepTime = delay-((System.nanoTime()-beforeTime)/1000000L);
			
			try {
				//actual sleep code
				if(sleepTime>0){
					this.sleep(sleepTime);
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(RPGFightThread.class.getName()).log(Level.SEVERE, null, ex);
			}
			fps = 1000/((System.nanoTime()-beforeTime)/1000000L);
		}
	}
	
	/**
	 * Changes whether the thread should run or not. 
	 * @param running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}


	/**
	 * @return Whether the thread is running or not.
	 */
	public boolean isRunning() {
		return running;
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		return mainScreen.onTouchEvent(event);
	}
}
