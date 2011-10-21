/**
 * 
 */
package com.vujicic.aleksa.rpgfight.sprite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

//TODO: FINISH JAVA DOCS

/**
 * @author Aleksa Vujicic
 *
 */
public class Text extends Sprite {
	
	protected String text;
	private Paint paint;
	private float size = 0;
	
	/**
	 * 
	 * @param context
	 * @param x
	 * @param y
	 * @param text
	 * @param paint
	 */
	public Text(Context context, float x, float y, String text, Paint paint) {
		super(context, 0, x, y, false);
		this.text = text;
		this.paint = paint;
	}
	
	/**
	 * 
	 * @param context
	 * @param x
	 * @param y
	 * @param text
	 * @param size
	 */
	public Text(Context context, float x, float y, String text, float size) {
		super(context, 0, x, y, false);
		this.text = text;
		this.size = size;
	}
	
	@Override
	public void draw(Canvas canvas, float xOrigin, float yOrigin) {
		Paint paint = this.paint;
		if (paint == null) {
			paint = new Paint();
			paint.setColor(Color.BLACK);
			paint.setAntiAlias(true);
			
			if (size != 0) paint.setTextSize(size);
			else paint.setTextSize(10);
		}
		
		canvas.drawText(text, xOrigin+x, yOrigin+y + paint.getTextSize()*0.75f, paint);
	}

	/**
	 * Changes the text displayed by the string
	 * 
	 * @param text New text
	 */
	public void setText(String text) {
		this.text = text;
	}
}
