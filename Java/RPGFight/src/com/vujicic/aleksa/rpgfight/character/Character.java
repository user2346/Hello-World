/**
 * 
 */
package com.vujicic.aleksa.rpgfight.character;

import android.util.Log;

// TODO: FINISH JAVA DOCS

/**
 * @author Aleksa Vujicic
 *
 */
public class Character {
	protected int icon;
	
	protected String name;
	protected String race;

	protected int maxHealth;
	protected int maxMana;
	protected int health;
	protected int mana;
	
	protected int exp;
	protected int level;
	protected int nextExp;

	protected int strength = 10;
	protected int defence = 10;
	protected int agility = 10;
	protected int wisdom = 10;

	protected int talentPoints;
	protected int skillPoints;

	public final static int MAX_STRENGTH = 2000;
	public final static int MAX_DEFENCE = 1000;
	public final static int MAX_AGILITY = 150;
	public final static int MAX_WISDOM = 1000;
	
	protected boolean isDead = false;
	
	
	/**
	 * Formula for level and exp required for next level.
	 * 
	 * @param level Current level.
	 * @return The nextExp value.
	 */
	protected static int getNextExpFromLevel(int level) {
		return (int)(10*Math.pow(level, 1.1));
	}
	
	/**
	 * @param icon The id for the image provide by the 'R' class. 
	 * @param name
	 * @param race
	 * @param health
	 * @param mana
	 * @param xp
	 * @param level
	 */
	public Character(int icon, String name, String race, int health, int mana, int totalExp) {
		this.icon = icon;
		this.name = name;
		this.race = race;
		this.maxHealth = health;
		this.maxMana = mana;
		this.health = health;
		this.mana = mana;
		this.exp = totalExp;
		nextExp = 1;
		level = 1;
		while (exp >= nextExp) levelUp();
	}
	
	/**
	 * 
	 * @param defender
	 */
	public void attackCharacter(Character defender) {
		if (isDead) return;
		int damDone = defender.attackedBy(this, (int)(Math.random()*100)+50);
		gainExp(damDone/100 + 1);
	}
	
	/**
	 * Makes this character lose health and possibly other effects. 
	 * If health becomes <=0, then isDead is set to 0;
	 * 
	 * @param attacker The character that attacked.
	 * @param damage The base damage for the attack.
	 */
	public int attackedBy(Character attacker, int damage) {
		int damageDone = (int)(damage*attacker.strength*attacker.strength*0.1*(Math.random()*0.1+0.45)/defence);
		health -= damageDone;
		if (health <= 0) {
			health = 0;
			isDead = true;
			attacker.gainExp(10*(int)Math.pow(level,2));;
		}
		return damageDone;
	}
	
	/**
	 * Adds a certain amount of exp to this character. Then checks for level ups.
	 * @param xp Amount to give.
	 */
	public void gainExp(int xp) {
		exp += xp;
		while (exp >= nextExp) levelUp();
	}
	
	/** 
	 * Levels up the character.
	 */
	public void levelUp() {
		level++;
		exp -= nextExp;
		nextExp = getNextExpFromLevel(level);
		maxHealth += level*100;
		health = maxHealth;
		Log.v("aLog", name + " is now level " + level);
	}
	
	/**
	 * @return The total amount of exp ever accumulated.
	 */
	public int getTotalExp() {
		int toReturn = 0;
		for (int i=1; i < level; i++) {
			toReturn += getNextExpFromLevel(i);
		}
		toReturn += exp;
		return toReturn;
	}
	
	/**
	 * @return Whether or not the character is dead.
	 */
	public boolean isDead() {
		return isDead;
	}
	
	public final void update() {
		if (strength > MAX_STRENGTH) strength = MAX_STRENGTH;
		if (defence > MAX_DEFENCE) defence = MAX_DEFENCE;
		if (agility > MAX_AGILITY) agility = MAX_AGILITY;
		if (wisdom > MAX_WISDOM) wisdom = MAX_WISDOM;
	}
}
