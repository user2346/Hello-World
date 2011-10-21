/**
 * 
 */
package com.vujicic.aleksa.rpgfight.sprite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Sprites are objects that are really just images. They have a position and can have child sprites.
 * 
 * @author Aleksa Vujicic
 *
 */
public class Sprite {
	/** The context. Usually application or activity context. */
	protected Context context;
	/** The image stored as a Bitmap object. */
	protected Bitmap bitmap; 
	/** Coordinate values. (0,0) = Top-Left. */
	public float x, y;
	/** List of children. Children are simply other sprites which are 'owned' by the parent sprite. */
	private List<Sprite> children;
	
	/**
	 * Simple constructor that puts in almost all values into variables. The exception being hasChildren and bitmapId.
	 * 
	 * @param context The context.
	 * @param bitmapId The id for the image provide by the 'R' class. 
	 * @param x The x position. 0 = Left.
	 * @param y The y position. 0 = Top.
	 * @param hasChildren If true, will initialise the children ArrayList.
	 */
	public Sprite(Context context, int bitmapId, float x, float y, boolean hasChildren) {
		this.context = context;
		this.bitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
		this.x = x;
		this.y = y;
		if (hasChildren) this.children = new ArrayList<Sprite>();
	}
	
	/**
	 * Constructor that creates a scaled version of an existing Bitmap.
	 * 
	 * @param context The context.
	 * @param x The x position. 0 = Left.
	 * @param y The y position. 0 = Top.
	 * @param hasChildren If true, will initialise the children ArrayList.
	 * @param bitmapId The id for the image provide by the 'R' class. 
	 * @param height The height for the new bitmap. 
	 * @param width The width for the new bitmap. 
	 */
	public Sprite(Context context, float x, float y, boolean hasChildren, int bitmapId, int height, int width) {
		this.context = context;
		this.bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), bitmapId), width, height, false);
		this.x = x;
		this.y = y;
		if (hasChildren) this.children = new ArrayList<Sprite>();
	}
	
	/**
	 * Draws the sprites onto a canvas. 
	 * 
	 * @param canvas The canvas on which to draw to.
	 * @param xOrigin The X value of the origin. E.g A sprite with x = 5 and xOrigin = 20, it will be drawn at 25 (because 5 out from the 20 mark).
	 * @param yOrigin The Y value of the origin. E.g A sprite with y = 10 and yOrigin = 25, it will be drawn at 35 (because 10 out from the 25 mark).
	 */
	public void draw(Canvas canvas, float xOrigin, float yOrigin) {
		canvas.drawBitmap(bitmap, x+xOrigin, y+yOrigin, null);
		
		if (children != null) {
			Iterator<Sprite> itr = children.iterator();
			while (itr.hasNext()){ // Draws all children as well
				itr.next().draw(canvas, x+xOrigin, y+yOrigin);
			}
		}
	}
	
	/**
	 * Use this method to update the state of a sprite. Make sure to call super.update()!
	 */
	public void update() {
		if (children != null) {
			Iterator<Sprite> itr = children.iterator();
			while (itr.hasNext()){ // Updates all children as well
				itr.next().update();
			}
		}
	}
	
	/**
	 * Adds a child to children. Will provide a warning if children = null.
	 * 
	 * @param child Child to add.
	 */
	public void addChild(Sprite child) {
		if (children != null) {
			children.add(child);
		} else {
			Log.w("aLog","Attempt to add child by:" + this.toString());
		}
	}

	/**
	 * Removes a child from children. Will provide a warning if children = null.
	 * 
	 * @param child Child to remove.
	 */
	public void removeChild(Sprite child) {
		if (children != null) {
			children.remove(child);
		} else {
			Log.w("aLog","Attempt to remove child by:" + this.toString());
		}
	}
	
	/**
	 * Removes all children. Will provide a warning if children = null.
	 */
	public void removeAllChildren() {
		if (children != null) {
			children = new ArrayList<Sprite>();
		} else {
			Log.w("aLog","Attempt to remove all children by:" + this.toString());
		}
	}

	/**
	 * Returns a list of all children. Will throw an error if children = null.
	 * 
	 * @return List of all children.
	 */
	public ArrayList<Sprite> getAllChildren() {
		if (children != null) {
			return (ArrayList<Sprite>) children;
		} else {
			Log.e("aLog","Attempt to access children by:" + this.toString());
			throw new RuntimeException("Attempt to access children by:" + this.toString());
		}
	}
	
	/**
	 * Moves the sprite by specified values.
	 * 
	 * @param x The x value of the movement. Negative values are for leftward movements.
	 * @param y The y value of the movement. Negative values are for upward movements.
	 */
	public void move(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	/**
	 * Returns whether or not a point is within a sprite.
	 * 
	 * @param px The x position.
	 * @param py The y position.
	 * @return Whether or not a point is a sprite.
	 * 
	 */
	public boolean containsPoint(float px, float py) {
		boolean containsX = (x < px && px < x+bitmap.getWidth());
		boolean containsY = (y < py && py < y+bitmap.getHeight());
		return (containsX && containsY);
	}
}
