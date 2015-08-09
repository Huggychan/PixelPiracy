package abilities;

import graphics.Animation;
import combat.Fighter;


public class AbilityTemplate 
{
	private String name;
	private AbilityEffect abilityEffect;
	private String description;
	private int manaCost;
	Animation animation;
	public AbilityTemplate(String string, String desc, int manaCost,Animation animation, AbilityEffect abilityEffect) 
	{
		this.manaCost = manaCost;
		this.name = string;
		this.description = desc;
		this.abilityEffect = abilityEffect;
		this.animation = animation;
	}
	public Ability createAbility(Fighter f)
	{
		return new Ability(name,description, f, manaCost,animation, abilityEffect);
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
