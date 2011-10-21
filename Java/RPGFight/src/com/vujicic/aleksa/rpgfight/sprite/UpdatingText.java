/**
 * 
 */
package com.vujicic.aleksa.rpgfight.sprite;

import android.content.Context;
import android.graphics.Paint;

//TODO: Either get rid of it or incase the int if necessary.

/**
 * Just a Text that is just followed by a changing number. Not fully implemented (might never be) and should NOT be used.
 * 
 * @author Aleksa Vujicic
 *
 */
@Deprecated
public class UpdatingText extends Text {
	
	private String textBefore;
	private int intToFollow;

	/**
	 * @param context
	 * @param x
	 * @param y
	 * @param text
	 * @param paint
	 */
	public UpdatingText(Context context, float x, float y, String text,
			Paint paint, int toFollow) {
		super(context, x, y, null, paint);
		this.intToFollow = toFollow;
		this.textBefore = text;
	}

	/**
	 * @param context
	 * @param x
	 * @param y
	 * @param text
	 * @param size
	 */
	public UpdatingText(Context context, float x, float y, String text,
			float size, int toFollow) {
		super(context, x, y, null, size);
		this.intToFollow = toFollow;
		this.textBefore = text;
	}
	
	@Override
	public void update() {
		setText(textBefore + intToFollow);
	}

}
