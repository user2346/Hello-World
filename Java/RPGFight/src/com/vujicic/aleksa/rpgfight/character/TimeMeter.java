/**
 * 
 */
package com.vujicic.aleksa.rpgfight.character;

import android.content.Context;

import com.vujicic.aleksa.rpgfight.R;
import com.vujicic.aleksa.rpgfight.sprite.Sprite;

//TODO: FINISH JAVA DOCS!

/**
 * @author Aleksa Vujicic
 *
 */
public class TimeMeter extends Sprite {

	public static TimeMeter timeMeter;
	private Sprite heroSprite;
	private Sprite enemySprite;
	
	public static TimeMeter createTimeMeter(Context context) {
		timeMeter = new TimeMeter(context, 175, 380);
		return timeMeter;
	}
	
	/**
	 * @param context
	 * @param bitmapId
	 * @param x
	 * @param y
	 * @param hasChildren
	 */
	private TimeMeter(Context context, float x, float y) {
		super(context, R.drawable.timemeter, x, y, true);

		heroSprite = new Sprite(context, 0, 15, false, CharacterInfo.heroInfo.character.icon, 45, 45);
		enemySprite = new Sprite(context, 0, 15, false, CharacterInfo.enemyInfo.character.icon, 45, 45);
		
		addChild(heroSprite);
		addChild(enemySprite);
	}
	
	@Override
	public void update() {
		heroSprite.x += CharacterInfo.heroInfo.character.agility/4;
		enemySprite.x += CharacterInfo.enemyInfo.character.agility/4;
		while (heroSprite.x >= 415) {
			heroSprite.x -= 415;
			if (CharacterInfo.heroAttack()) {
				removeChild(enemySprite);
				enemySprite = new Sprite(context, 0, 15, false, CharacterInfo.enemyInfo.character.icon, 45, 45);
				addChild(enemySprite);
			}
		}
		while (enemySprite.x >= 415) {
			enemySprite.x -= 415;
			CharacterInfo.enemyAttack();
		}
	}
}
