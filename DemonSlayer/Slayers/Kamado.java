package Slayers;
public class Kamado extends Slayers{
    public Kamado(String name, int health, int maxDamage, int lowestDamage) {
        super(name, health, maxDamage, lowestDamage);
    }
    @Override
    public String evade(int damage){
        return "Tanjiro evaded but took "+Math.ceil(damage*0.20)+" damage.";
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
//        String newHealth = getName()+"s current health is "+getHealth()+".\n";
//        String newDamageDealt = "Damage done "+(int)Math.round(damageDealt*0.70)+".\n";
//        String healsRemaining = "Heals remaining "+getHeals()+". ";
//        if (getHealth() <= 0)
//            newHealth = getName()+"s current health is 0.\n";
//        return newHealth + newDamageDealt + healsRemaining;
//    }
}