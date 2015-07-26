package fighters;

import java.util.Random;

import worldGeneration.NameGenerator;
import graphics.ImageManager;
import abilities.Ability;

public class FighterSpawner 
{
	public static Fighter captain(int health, int attack, int speed, int magic)
	{
		Fighter captain = new Fighter(ImageManager.getImage(ImageManager.CAPTAIN),NameGenerator.captainName(),health,attack,magic, speed,3);
		captain.learnAbility(Ability.punch(captain));
		captain.learnAbility(Ability.heal(captain));
		return captain;
	}

	public static Fighter pirate(Random r) {
		Fighter pirate = new Fighter(ImageManager.getImage(ImageManager.PIRATE1),NameGenerator.crewMemberName(),10,1,1,1,1);
		pirate.learnAbility(Ability.punch(pirate));
		pirate.learnAbility(Ability.kick(pirate));
		return pirate;
	}
}

//public class Captain extends Fighter 
//{
//	public Captain(int width, int height, int health, int attack,
//			int speed, int def, int hpGrowth, int atkGrowth, int spdGrowth,
//			int dfGrowth)
//	{
//		super(ImageManager.getImage(ImageManager.CAPTAIN), width, height, health, attack, speed, def, hpGrowth, atkGrowth,
//				spdGrowth, dfGrowth);
//		this.abilities = new Ability[]{new Kick(), new Heal()};
//		
//	}
//}
