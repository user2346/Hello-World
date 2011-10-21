/**
 * 
 */
package com.vujicic.aleksa.rpgfight.sprite;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.view.MotionEvent;

import com.vujicic.aleksa.rpgfight.R;
import com.vujicic.aleksa.rpgfight.Touchable;
import com.vujicic.aleksa.rpgfight.character.CharacterInfo;
import com.vujicic.aleksa.rpgfight.character.Enemy;
import com.vujicic.aleksa.rpgfight.character.Hero;
import com.vujicic.aleksa.rpgfight.character.TimeMeter;

//TODO: FINISH JAVA DOCS

/**
 * Basically a screen object contains all the sprites as children (or 'grand-children') and is used to handle all events and draw etc. Implements Touchable.
 * 
 * @author Aleksa Vujicic
 * 
 */
public class Screen extends Sprite implements Touchable {

	private int screenHeight;
	private int screenWidth;
	
	/**
	 * Simple constructor.
	 * 
	 * @param context The context.
	 * @param background The background image.
	 * @param width 
	 * @param height 
	 */
	public Screen(Context context, int background, int height, int width) {
		super(context, background, 0, 0, true);
		Hero c1 = new Hero(R.drawable.stickhero, "Hero", "Human/Ogre", 10000, 5000, 100);
		Enemy c2 = new Enemy(R.drawable.enemy, "BaddyGuy35", "Orc", 5000, 1000, 0);
		addChild(CharacterInfo.createHeroInfo(context, c1));
		addChild(CharacterInfo.createEnemyInfo(context, c2));
		addChild(TimeMeter.createTimeMeter(context));
		screenHeight = height;
		screenWidth = width;
	}
	
	@Override
	public void update() {
		removeAllChildren();
		addChild(CharacterInfo.heroInfo);
		addChild(CharacterInfo.enemyInfo);
		addChild(TimeMeter.timeMeter);
		TimeMeter.timeMeter.update();
		super.update();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		ArrayList<Sprite> children = getAllChildren();
		Iterator<Sprite> itr = children.iterator();
		boolean successful = false;
		
		while (itr.hasNext()){
			Sprite child = itr.next();
			if (child instanceof Touchable && child.containsPoint(event.getX(), event.getY())) {
				Touchable touch = (Touchable) child;
				if (touch.onTouchEvent(event)) {
					successful = true;
					break;
				}
			}
		}
		
		return successful;
	}
}
