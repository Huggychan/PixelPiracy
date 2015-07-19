package fighters;

import java.util.Random;

import graphics.ImageManager;
import abilities.Ability;

public class FighterSpawner 
{
	public static Fighter captain(int health, int attack, int speed, int magic)
	{
		Fighter captain = new Fighter(ImageManager.getImage(ImageManager.CAPTAIN),"Captain", 10,10,health*10,attack,speed,magic,health, attack/3,speed/3,magic/3);
		captain.learnAbility(Ability.punch(captain));
		captain.learnAbility(Ability.heal(captain));
		return captain;
	}

	public static Fighter pirate(Random r) {
		Fighter pirate = new Fighter(ImageManager.getImage(ImageManager.PIRATE1),"Pirate", 10,10,(10*r.nextInt(10))+1,(r.nextInt(10)+1),r.nextInt(10)+1,r.nextInt(10)+1,(r.nextInt(10)+1)/2, (r.nextInt(10)+1)/2,(r.nextInt(10)+1)/2 , (r.nextInt(10)+1)/2);
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
