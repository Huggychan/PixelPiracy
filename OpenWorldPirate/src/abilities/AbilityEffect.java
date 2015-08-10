package abilities;

import characterManagement.Fighter;


public interface AbilityEffect 
{

	public void use(Fighter[] self, Fighter[] enemyFighters, Fighter fighter);
}
