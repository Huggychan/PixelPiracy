package characterManagement;

import worldGeneration.NameGenerator;
import graphics.ImageManager;
import abilities.Ability;
//1. Health 2. Attack 3. Magic 4. Speed 5. growth

public class FighterSpawner 
{
	public static Fighter captain(int health, int attack, int speed, int magic)
	{
		Fighter captain = new Fighter(ImageManager.getImage(ImageManager.CAPTAIN),NameGenerator.captainName(),health,attack,magic, speed,2);
		captain.learnAbility(Ability.punch(captain));
		captain.learnAbility(Ability.heal(captain));
		captain.learnAbility(Ability.manaHeal(captain));
		return captain;
	}
	public static Fighter pirate(int str) {
		Fighter pirate = new Fighter(ImageManager.getImage(ImageManager.PIRATE1),NameGenerator.crewMemberName(),str,str/20 + 1,str/20 + 1,str/20 + 1,1);
		pirate.learnAbility(Ability.punch(pirate));
		pirate.learnAbility(Ability.kick(pirate));
		return pirate;
	}
	public static Fighter pirateWizard(int str)
	{
		Fighter pirate = new Fighter(ImageManager.getImage(ImageManager.PIRATEWIZARD), NameGenerator.crewMemberName(), str/2, str/20+1, str/10 + 1, str/20+1, 1);
		pirate.learnAbility(Ability.TidalWave(pirate));
		pirate.learnAbility(Ability.heal(pirate));
		pirate.learnAbility(Ability.manaHeal(pirate));
		return pirate;
	}
	
	public static Fighter monkey()
	{
		Fighter monkey = new Fighter(ImageManager.getImage(ImageManager.MONKEY), "Monkey", 15,2,0,1,1);
		monkey.learnAbility(Ability.bananaToss(monkey));
		return monkey;
	}
}
