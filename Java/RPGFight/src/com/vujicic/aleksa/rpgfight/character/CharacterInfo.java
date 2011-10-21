/**
 * 
 */
package com.vujicic.aleksa.rpgfight.character;

import android.content.Context;
import android.graphics.Color;

import com.vujicic.aleksa.rpgfight.R;
import com.vujicic.aleksa.rpgfight.sprite.Bar;
import com.vujicic.aleksa.rpgfight.sprite.Sprite;
import com.vujicic.aleksa.rpgfight.sprite.Text;

//TODO: FINISH JAVA DOCS

/**
 * @author Aleksa Vujicic
 *
 */
public class CharacterInfo extends Sprite {

	static public CharacterInfo heroInfo;
	static public CharacterInfo enemyInfo;
	
	protected boolean onLeft;
	protected Character character;
	private Bar healthBar;
	private Bar manaBar;
	private Bar expBar;
	private Text levelText;
	private Text str;
	private Text def;
	private Text agi;
	private Text wis;
	
	public static CharacterInfo createHeroInfo(Context context, Hero ch) {
		heroInfo = new CharacterInfo(context, true, 50, 50, ch);
		return heroInfo;
	}
	
	public static CharacterInfo createEnemyInfo(Context context, Enemy ch) {
		enemyInfo = new CharacterInfo(context, false, 570, 50, ch);
		return enemyInfo;
	}
	
	public static void enemyAttack() {
		enemyInfo.character.attackCharacter(heroInfo.character);
	}
	
	public static boolean heroAttack() {
		heroInfo.character.attackCharacter(enemyInfo.character);
		if (enemyInfo.character.isDead()) {
			createEnemyInfo(enemyInfo.context, new Enemy(R.drawable.enemy, "BaddyGuy357", "Orc", 5000, 1000, (int)(Math.pow(heroInfo.character.level,2)*(Math.random()+0.5)*10)));
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param context
	 * @param onLeft 
	 * @param x
	 * @param y
	 */
	private CharacterInfo(Context context, boolean onLeft, float x, float y, Character ch) {
		super(context, (onLeft ? R.drawable.character_info_bg_left : R.drawable.character_info_bg_right), x, y, true);
		this.character = ch;
		this.onLeft = onLeft;
		
		int textX;
		if (onLeft) {
			addChild(new Sprite(context, character.icon, 3, 3, false));
			textX = 65;
		} else {
			addChild(new Sprite(context, character.icon, 116, 3, false));
			textX = 5;
		}
		
		addChild(new Text(context, textX, 5, character.name, 18));
		addChild(new Text(context, textX, 25, character.race, 18));
		
		levelText = new Text(context, textX, 45, "Lvl " + character.level, 18);
		addChild(levelText);

		str = new Text(context, 5, 150, "Str: " + character.strength, 14);
		def = new Text(context, 5, 170, "Def: " + character.defence, 14);
		agi = new Text(context, 95, 150, "Agi: " + character.agility, 14);
		wis = new Text(context, 95, 170, "Wis: " + character.wisdom, 14);
		addChild(str);
		addChild(def);
		addChild(agi);
		addChild(wis);
		
		healthBar = new Bar(context, 5, 70, character.health, character.maxHealth, Color.RED, 18, 170);
		manaBar = new Bar(context, 5, 95, character.mana, character.maxMana, Color.BLUE, 18, 170);
		expBar = new Bar(context, 5, 120, character.exp, character.nextExp, Color.GREEN, 10, 170);
		addChild(healthBar);
		addChild(manaBar);
		addChild(expBar);
	}
	
	@Override
	public void update() {
		character.update();
		healthBar.updateMinAndMax(character.health, character.maxHealth);
		manaBar.updateMinAndMax(character.mana, character.maxMana);
		expBar.updateMinAndMax(character.exp, character.nextExp);
		levelText.setText("Lvl " + character.level);
		str.setText("Str: " + character.strength);
		def.setText("Def: " + character.defence);
		agi.setText("Agi: " + character.agility);
		wis.setText("Wis: " + character.wisdom);
		super.update();
	}

}
