package Slayers;

import java.util.Random;

public class Slayers{
    private String name = "";
    private int health = 100;
    private int maxDamage = 12;
    private int lowestDamage = 10;
    private int damageAbsorbed;
    private int damageDealt;
    Slayers(String name, int health, int maxDamage, int lowestDamage){
        this.name = name;
        this.health = health;
        this.maxDamage = maxDamage;
        this.lowestDamage = lowestDamage;
    }
    public int dealDamage(){
        damageDealt = new Random().nextInt(maxDamage-lowestDamage+1)+lowestDamage;
        return damageDealt;
    }
    public String evade(int damage) {
        return name + "evaded successful";
    }
    public String run(String enemy) {
        return "You ran away from "+ enemy + " but you lost 1 life.";
    }
    public int getHealth() {
        return health;
    }
    public void increaseHealth(int health){
        this.health += health;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public String getName(){
        return this.name;
    }
    public int getMaxDamage(){
        return this.maxDamage;
    }
    public int getLowestDamage(){
        return this.lowestDamage;
    }
    public int getDamageDealt(){
        return this.damageDealt;
    }
    public int getDamageAbsorbed(){
        return this.damageAbsorbed;
    }
    public void gotHit(int damage){
        health -= damage;
        damageAbsorbed = damage;
    }
    public String printStatsEvade() {
        String newHealth = name+"s health is "+health+".\n";
//        String healsRemaining = "Heals remaining "+heals+".";
        if (health <= 0)
            newHealth = name+"s current health is 0.\n";
        return newHealth + "You evaded an attack.\n";// + healsRemaining;
    }
}
