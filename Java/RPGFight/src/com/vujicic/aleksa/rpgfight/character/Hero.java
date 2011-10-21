/**
 * 
 */
package com.vujicic.aleksa.rpgfight.character;

/**
 * @author Aleksa Vujicic
 *
 */
public class Hero extends Character {

	/**
	 * @param icon
	 * @param name
	 * @param race
	 * @param health
	 * @param mana
	 * @param totalExp
	 */
	public Hero(int icon, String name, String race, int health, int mana,
			int totalExp) {
		super(icon, name, race, health, mana, totalExp);
	}
	
	@Override
	public void levelUp() {
		strength+=7;
		defence+=5;
		agility+=3;
		super.levelUp();
	}
}
