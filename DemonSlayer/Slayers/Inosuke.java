package Slayers;

public class Inosuke extends Slayers{
    public Inosuke(String name, int health, int maxDamage, int lowestDamage) {
        super(name, health, maxDamage, lowestDamage);
    }
    @Override
    public String evade(int damage){
        return "Inosuke couldn't evade the attack.";
    }
    public String printStats(){
        String newHealth = getName()+"s health is "+getHealth()+".\n";
        String newDamageDealt = "Damage done "+getDamageDealt()+".\n";
//        String healsRemaining = "Heals remaining "+DemonSlayer+".";
        if (getHealth() <= 0)
            newHealth = getName()+"s current health is 0.\n";
        return newHealth + newDamageDealt;// + healsRemaining;
    }
//    public String printStatsMuzan(){
//        String newHealth = getName()+"s health is "+getName()+".\n";
//        String newDamageDealt = "Damage done "+(int)Math.round(getDamageDealt()*0.70)+".\n";
//        String healsRemaining = "Heals remaining "+getHeals()+".";
//        if (getHealth() <= 0)
//            newHealth = getName()+"s current health is 0.\n";
//        return newHealth + newDamageDealt + healsRemaining;
//    }
}
