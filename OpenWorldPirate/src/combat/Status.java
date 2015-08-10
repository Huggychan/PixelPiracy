package combat;

import characterManagement.Fighter;


public abstract class Status 
{
	Fighter affected;
	public Status(Fighter f)
	{
		this.affected = f;
	}
	public abstract void trigger();
}
