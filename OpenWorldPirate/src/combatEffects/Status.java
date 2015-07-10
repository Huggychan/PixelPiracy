package combatEffects;

import fighters.Fighter;

public abstract class Status 
{
	Fighter affected;
	public Status(Fighter f)
	{
		this.affected = f;
	}
	public abstract void trigger();
}
