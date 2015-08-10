package abilities;

import gameStates.GameState;
import graphics.Animation;
import graphics.AnimationBank;
import gui.GameInstance;

import java.util.Random;

import characterManagement.Fighter;



public class Ability {
	private String name;
	private AbilityEffect abilityEffect;
	private Fighter parent;
	private String description;
	private int manaCost;
	private Animation animation;
	public Ability(String string, String desc,Fighter fighter,int manaCost,Animation animation,AbilityEffect abilityEffect) 
	{
		this.name = string;
		this.description = desc;
		this.abilityEffect = abilityEffect;
		this.parent = fighter;
		this.manaCost = manaCost;
		this.animation = animation;
	}

	public String getName()
	{
		return name;
	}
	public void play(GameState toPlayOver, GameInstance gi, int xOrigin, int yOrigin)
	{
		if(animation != null)
		{
			animation.play(toPlayOver, gi, xOrigin, yOrigin);
		}
	}
	public void use(Fighter[] self, Fighter[] enemyFighters)
	{

		abilityEffect.use(self, enemyFighters, parent);
	}
	public AbilityTemplate getTemplate()
	{
		return new AbilityTemplate(name, description,manaCost,animation, abilityEffect);
	}

	public String getDescription() {
		return description;
	}
	
	public static Ability heal(Fighter f)
	{
		Ability heal = new Ability("Heal", "Heals the team by your magic power", f,20, null,new AbilityEffect(){

			@Override
			public void use(Fighter[] self, Fighter[] enemyFighters, Fighter fighter) 
			{
				for(int i = 0; i < self.length; i++)
				{
					if(self[i] !=null)
					{
						self[i].heal(fighter.getMagicPower());
					}
				}
			}});
		return heal;
	}
	

	public static Ability punch(Fighter f) 
	{
		return new Ability("Punch","Punches the opposing front line for your attack", f,0,null, new AbilityEffect(){

			@Override
			public void use(Fighter[] self, Fighter[] enemyFighters,
					Fighter fighter) 
			{
			boolean hasHit = false;
			for(int i = 0; i < enemyFighters.length; i++)
			{
				if(!hasHit)
				{
					if(enemyFighters[i] != null && enemyFighters[i].getLiving())
					{
						enemyFighters[i].getHit(fighter.getAttack());
						hasHit = true;
					}
				}
			}
			}});
	}
	public static Ability kick(Fighter f) 
	{
		return new Ability("Kick","kicks the opposing front line for double your attack", f,20,null, new AbilityEffect(){

			@Override
			public void use(Fighter[] self, Fighter[] enemyFighters,
					Fighter fighter) 
			{
			boolean hasHit = false;
			for(int i = 0; i < enemyFighters.length; i++)
			{
				if(!hasHit)
				{
					if(enemyFighters[i] != null && enemyFighters[i].getLiving())
					{
						enemyFighters[i].getHit(fighter.getAttack()+1);
						hasHit = true;
					}
				}
			}
			}});
	}


	public static Ability TidalWave(Fighter f) 
	{
		return new Ability("Tidal Wave","Powerful AoE attack", f,60,null, new AbilityEffect(){

			@Override
			public void use(Fighter[] self, Fighter[] enemyFighters,
					Fighter fighter) 
			{
				for(int i = 0; i < enemyFighters.length; i++)
				{
					if(enemyFighters[i] !=null)
					{
						enemyFighters[i].getHit(fighter.getMagicPower());
					}
				}
			}});
	}

	public int getManaCost() {
		// TODO Auto-generated method stub
		return manaCost;
	}

	public static Ability manaHeal(Fighter pirate) 
	{
		return new Ability("Mana Heal","Restores the teams mana", pirate,0,null, new AbilityEffect(){

			@Override
			public void use(Fighter[] self, Fighter[] enemyFighters,
					Fighter fighter) 
			{
				for(int i = 0; i < self.length; i++)
				{
					if(self[i] !=null)
					{
						self[i].restoreMana(fighter.getMagicPower()*10);
					}
				}
			}});
	}

	public static Ability bananaToss(Fighter monkey) 
	{
		return new Ability("Banana Toss","Randomly Strikes an enemy", monkey,0,AnimationBank.bananaToss, new AbilityEffect(){

			@Override
			public void use(Fighter[] self, Fighter[] enemyFighters,
					Fighter fighter) 
			{
				Random r = new Random();
				boolean hasHit = false;
				while(!hasHit)
				{
					int temp = r.nextInt(enemyFighters.length);
					if(enemyFighters[temp] != null && enemyFighters[temp].getLiving())
					{
						enemyFighters[temp].getHit(monkey.getAttack()+1);
						hasHit = true;
					}
				}
				
			}});
	}

	
}
