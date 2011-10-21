/**
 * 
 */
package com.vujicic.aleksa.rpgfight;

import android.view.MotionEvent;

/**
 * A class that implements Touchable can handle MotionEvents.
 * 
 * @author Aleksa Vujicic
 *
 */
public interface Touchable {
	/**
	 * Handles a motion event.
	 * 
	 * @param event The event to be handled.
	 * @return Whether the event was handled.
	 */
	public boolean onTouchEvent(MotionEvent event);
}
