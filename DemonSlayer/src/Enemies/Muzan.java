package Enemies;

import java.util.Random;

public class Muzan implements Demons{

    Random random = new Random();
    
	private int maxDamage = 13;
    private int lowestDamage = 7;
	public int healthMuzan = 300;
	
	String name = "Muzan";
    int damageAbsorbed;

	@Override
	public int dealDamage() {
		return random.nextInt(maxDamage-lowestDamage)+lowestDamage;
	}
	@Override
	public void gotHit(int damage) {
		this.healthMuzan -= damage;
		this.damageAbsorbed = damage;
	}
	@Override
	public void takeDamage(int damage) {
		healthMuzan -= damage;
	}
    @Override
	public String toString(){
		if (healthMuzan <= 0)
			return name+"s health is 0.\n";
		return name+"s health is "+healthMuzan+".\n";
	}
}
