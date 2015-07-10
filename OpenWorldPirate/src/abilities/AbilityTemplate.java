package abilities;

import fighters.Fighter;

public class AbilityTemplate 
{
	private String name;
	private AbilityEffect abilityEffect;
	private String description;
	private int manaCost;
	public AbilityTemplate(String string, String desc, int manaCost, AbilityEffect abilityEffect) 
	{
		this.manaCost = manaCost;
		this.name = string;
		this.description = desc;
		this.abilityEffect = abilityEffect;
	}
	public Ability createAbility(Fighter f)
	{
		return new Ability(name,description, f, manaCost, abilityEffect);
	}
	public String getName() 
	{
		return name;
	}
	public String getDescription() 
	{
		return description;
	}
}
