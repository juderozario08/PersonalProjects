package Enemies;

import java.util.Random;

public class Akaza implements Demons{
	private int maxDamage = 14;
    private int lowestDamage = 9;
	public int akazaHealth = 280;
	
	String name = "Akaza";
    int damageAbsorbed;

	@Override
	public int dealDamage() {
		return new Random().nextInt(maxDamage-lowestDamage)+lowestDamage;
	}
	@Override
	public void takeDamage(int damage) {
		akazaHealth -= damage;
	}

	@Override
	public void gotHit(int damage) {
		this.akazaHealth -= damage;
		this.damageAbsorbed = damage;
	}

	public String toString(){
		if (akazaHealth <= 0)
			return name+"s health is 0.\n";
		return name+"s health is "+akazaHealth+".\n";
	}
}
