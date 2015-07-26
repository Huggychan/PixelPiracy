package abilities;

import fighters.Fighter;

public class Ability {
	private String name;
	private AbilityEffect abilityEffect;
	private Fighter parent;
	private String description;
	private int manaCost;
	public Ability(String string, String desc,Fighter fighter,int manaCost, AbilityEffect abilityEffect) 
	{
		this.name = string;
		this.description = desc;
		this.abilityEffect = abilityEffect;
		this.parent = fighter;
		this.manaCost = manaCost;
	}

	public String getName()
	{
		return name;
	}

	public void use(Fighter[] self, Fighter[] enemyFighters)
	{
		abilityEffect.use(self, enemyFighters, parent);
	}
	
	public static Ability heal(Fighter f)
	{
		Ability heal = new Ability("Heal", "Heals the team by your magic power", f,20, new AbilityEffect(){

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
		return new Ability("Punch","Punches the opposing front line for your attack", f,0, new AbilityEffect(){

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
		return new Ability("Kick","kicks the opposing front line for double your attack", f,20, new AbilityEffect(){

			@Override
			public void use(Fighter[] self, Fighter[] enemyFighters,
					Fighter fighter) 
			{
			boolean hasHit = false;
			for(int i = 0; i < enemyFighters.length; i++)
			{
				if(!hasHit)
				{
					if(enemyFighters[i] != null)
					{
						enemyFighters[i].getHit(fighter.getAttack()+2);
						hasHit = true;
					}
				}
			}
			}});
	}
	public AbilityTemplate getTemplate()
	{
		return new AbilityTemplate(name, description,manaCost, abilityEffect);
	}

	public String getDescription() {
		return description;
	}

	public static Ability TidalWave(Fighter f) 
	{
		return new Ability("Tidal Wave","Powerful AoE attack", f,60, new AbilityEffect(){

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

	
}
