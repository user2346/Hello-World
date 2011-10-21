/**
 * 
 */
package com.vujicic.aleksa.rpgfight.character;

/**
 * @author Aleksa Vujicic
 *
 */
public class Enemy extends Character {

	/**
	 * @param icon
	 * @param name
	 * @param race
	 * @param health
	 * @param mana
	 * @param totalExp
	 */
	public Enemy(int icon, String name, String race, int health, int mana,
			int totalExp) {
		super(icon, name, race, health, mana, totalExp);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void levelUp() {
		strength+=2;
		defence+=2;
		agility+=2;
		super.levelUp();
	}
}
