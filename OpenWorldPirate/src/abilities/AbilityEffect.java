package abilities;

import combat.Fighter;


public interface AbilityEffect 
{

	public void use(Fighter[] self, Fighter[] enemyFighters, Fighter fighter);
}
