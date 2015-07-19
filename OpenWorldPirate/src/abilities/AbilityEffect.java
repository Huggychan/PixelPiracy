package abilities;

import fighters.Fighter;

public interface AbilityEffect 
{

	public void use(Fighter[] self, Fighter[] enemyFighters, Fighter fighter);
}
