package Enemies;

import java.util.Random;

public class Rui implements Demons{
    
    Random random = new Random();
    
	private int maxDamage = 10;
    private int lowestDamage = 5;
	public int healthRui = 250;
	String name = "Rui";
    int damageAbsorbed;

	@Override
	public int dealDamage() {
		return random.nextInt(maxDamage-lowestDamage)+lowestDamage;
	}
	@Override
	public void gotHit(int damage) {
		this.healthRui -= damage;
		this.damageAbsorbed = damage;
	}
	@Override
	public void takeDamage(int damage) {
		healthRui -= damage;
	}
	public String toString(){
		if (healthRui <= 0)
			return name+"s health is 0.\n";
		return name+"s health is "+healthRui+".\n";
	} 
}
