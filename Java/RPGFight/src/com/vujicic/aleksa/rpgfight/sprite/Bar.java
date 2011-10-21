/** 
 * 
 */
package com.vujicic.aleksa.rpgfight.sprite;

import java.text.NumberFormat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;

import com.vujicic.aleksa.rpgfight.R;

//TODO: FINISH JAVA DOCS

/**
 * @author Aleksa Vujicic
 *
 */
public class Bar extends Sprite {
	
	protected Text text;
	protected int barWidth;
	protected int barHeight;
	protected int color;
	
	/**
	 * 
	 * @param context
	 * @param x
	 * @param y
	 * @param color
	 * @param size
	 */
	public Bar(Context context, float x, float y, long min, long max, int color, float size, int barWidth) {
		super(context, 0, x, y, true);
		
		this.color = color;
		
		this.barWidth = barWidth;
		this.barHeight = (int)size+5;
		
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setTextSize(size-2);
		paint.setTextAlign(Paint.Align.CENTER);
		
		text = new Text(context, barWidth/2, 2, "Loading...", paint);
		addChild(text);
		updateMinAndMax(min, max);
	}
	
	/**
	 * 
	 * @param min
	 * @param max
	 */
	public void updateMinAndMax(long min, long max) {
		text.setText(NumberFormat.getNumberInstance().format(min) + "/" + NumberFormat.getNumberInstance().format(max));

		long barLevel = (long)(barWidth*min/max);
		Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.white);
		Bitmap bar = Bitmap.createScaledBitmap(b, barWidth, barHeight, false);
		
	    for(int px = 0;px < barWidth;px++) {
	        for(int py = 0;py < barHeight;py++) {
	        	if (px >= barLevel) bar.setPixel(px, py, Color.GRAY);
	        	else bar.setPixel(px, py, color);
	        }
	    }
	    
	    bitmap = bar;
	}

}
