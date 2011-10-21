/**
 * 
 */
package com.vujicic.aleksa.rpgfight;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//TODO: FINISH JAVA DOCS

/**
 * @author Aleksa Vujicic
 *
 */
public class RPGFightView extends SurfaceView implements SurfaceHolder.Callback {
	SurfaceHolder surfaceHolder;
	Context context;
	
	private RPGFightThread thread;
	
	void InitView(){
		getHolder().addCallback(this);
//		thread = new RPGFightThread(getHolder(), context, this);
		setFocusable(true);
	}
	
	public RPGFightView(Context context){
		super(context);
		this.context = context;
		InitView();
	}
	
	@Override 
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {}
	
	@Override 
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	@Override 
	public void surfaceCreated(SurfaceHolder arg0) {
		thread = new RPGFightThread(getHolder(), context, this);
		thread.setRunning(true);
		thread.start();
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		return thread.onTouchEvent(event);
	}
}
